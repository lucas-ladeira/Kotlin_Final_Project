package com.example.projetfinal

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CropListActivity : AppCompatActivity() {
    private lateinit var listViewCrops: ListView
    private lateinit var adapter: CropAdapter
    private lateinit var dbHelper: CropDatabaseHelper
    private lateinit var db: SQLiteDatabase
    private lateinit var cropDAO: CropDAO
    private lateinit var addButton: FloatingActionButton
    private lateinit var txtViewCurrentTemp: TextView
    private lateinit var txtViewCurrentHum: TextView

    private var currentTemperature: Double = 0.0
    private var currentHumidity: Double = 0.0

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                refreshCrops()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_list)

        dbHelper = CropDatabaseHelper(this)
        db = dbHelper.writableDatabase
        cropDAO = CropDAO(db)

        listViewCrops = findViewById(R.id.listViewCrops)
        adapter = CropAdapter(this, mutableListOf())
        listViewCrops.adapter = adapter
        refreshCrops()

        addButton = findViewById(R.id.addButton)
        txtViewCurrentTemp = findViewById(R.id.txtViewCurrentTemperature)
        txtViewCurrentHum = findViewById(R.id.txtViewCurrentHumidity)

        listViewCrops.setOnItemClickListener { _, _, position, _ ->
            val crop = adapter.getItem(position)
            val intent = Intent(this, ManageCropActivity::class.java)
            intent.putExtra("crop_id", crop.id)
            startForResult.launch(intent)
        }

        listViewCrops.setOnItemLongClickListener { _, _, position, _ ->
            val crop = adapter.getItem(position)
            AlertDialog.Builder(this)
                .setTitle("Delete crop")
                .setMessage("Are you sure you want to delete ${crop.name}?")
                .setPositiveButton("Yes") { _, _ ->
                    cropDAO.deleteCrop(crop.id)
                    refreshCrops()
                }
                .setNegativeButton("No", null)
                .show()
            true
        }

        addButton.setOnClickListener {
            val intent = Intent(this, ManageCropActivity::class.java)
            startForResult.launch(intent)
        }
    }

    private fun refreshCrops() {
        val crops = cropDAO.getAllCrops()
        adapter.updateCrops(crops.toMutableList())
    }
}