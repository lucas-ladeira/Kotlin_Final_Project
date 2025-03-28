package com.example.projetfinal

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ManageCropActivity : AppCompatActivity() {
    private lateinit var editTxtCropName: EditText
    private lateinit var editTxtMaxTemp: EditText
    private lateinit var editTxtMinTemp: EditText
    private lateinit var editTxtMinHum: EditText
    private lateinit var editTxtMaxHum: EditText
    private lateinit var editTxtLatitude: EditText
    private lateinit var editTxtLongitude: EditText
    private lateinit var editTxtComments: EditText
    private lateinit var editTxtDateChoice: EditText
    private lateinit var rdGroupCropType: RadioGroup
    private lateinit var rdBtnCereal: RadioButton
    private lateinit var rdBtnVegetable: RadioButton
    private lateinit var rdBtnFruit: RadioButton
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_crop)

        editTxtCropName = findViewById(R.id.editTxtCropName)
        editTxtMaxTemp = findViewById(R.id.editTxtMaxTemp)
        editTxtMinTemp = findViewById(R.id.editTxtMinTemp)
        editTxtMinHum = findViewById(R.id.editTxtMinHum)
        editTxtMaxHum = findViewById(R.id.editTxtMaxHum)
        editTxtLatitude = findViewById(R.id.editTxtLatitude)
        editTxtLongitude = findViewById(R.id.editTxtLongitude)
        editTxtComments = findViewById(R.id.editTxtComments)
        editTxtDateChoice = findViewById(R.id.editTxtDateChoice)
        rdGroupCropType = findViewById(R.id.rdGroupCropType)
        rdBtnCereal = findViewById(R.id.rdBtnCereal)
        rdBtnVegetable = findViewById(R.id.rdBtnVegetable)
        rdBtnFruit = findViewById(R.id.rdBtnFruit)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)

        // TODO: Dealing with form CRUD

        btnSave.setOnClickListener {
            // TODO: Handle save button click
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }
}