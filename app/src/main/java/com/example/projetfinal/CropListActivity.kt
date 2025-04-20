package com.example.projetfinal

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CropListActivity : BaseActivity() {
    private lateinit var listViewCrops: ListView
    private lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_list)

        listViewCrops = findViewById(R.id.listViewCrops)
        addButton = findViewById(R.id.addButton)

        listViewCrops.setOnItemClickListener { _, _, position, _ ->
            // TODO: Handle item click
        }

        listViewCrops.setOnItemLongClickListener { _, _, position, _ ->
            // TODO: Handle item long click
            true
        }

        addButton.setOnClickListener {
            val intent = Intent(this, ManageCropActivity::class.java)
            startActivity(intent)
        }
    }
}