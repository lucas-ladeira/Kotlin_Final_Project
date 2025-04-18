package com.example.projetfinal

import android.app.DatePickerDialog
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ManageCropActivity : BaseActivity() {
    private lateinit var editTxtCropName: EditText
    private lateinit var editTxtMaxTemp: EditText
    private lateinit var editTxtMinTemp: EditText
    private lateinit var editTxtMinHum: EditText
    private lateinit var editTxtMaxHum: EditText
    private lateinit var editTxtLatitude: EditText
    private lateinit var editTxtLongitude: EditText
    private lateinit var editTxtComments: EditText
    private lateinit var editTxtPlantingDate: EditText
    private lateinit var rdGroupCropType: RadioGroup
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    private var cropId: Int = -1
    private var plantingDate: String = ""

    private lateinit var dbHelper: CropDatabaseHelper
    private lateinit var db: SQLiteDatabase
    private lateinit var cropDAO: CropDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_crop)

        dbHelper = CropDatabaseHelper(this)
        db = dbHelper.writableDatabase
        cropDAO = CropDAO(db)

        editTxtCropName = findViewById(R.id.editTxtCropName)
        editTxtMaxTemp = findViewById(R.id.editTxtMaxTemp)
        editTxtMinTemp = findViewById(R.id.editTxtMinTemp)
        editTxtMinHum = findViewById(R.id.editTxtMinHum)
        editTxtMaxHum = findViewById(R.id.editTxtMaxHum)
        editTxtLatitude = findViewById(R.id.editTxtLatitude)
        editTxtLongitude = findViewById(R.id.editTxtLongitude)
        editTxtComments = findViewById(R.id.editTxtComments)
        editTxtPlantingDate = findViewById(R.id.editTxtPlantingDate)
        rdGroupCropType = findViewById(R.id.rdGroupCropType)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)

        cropId = intent.getIntExtra("crop_id", -1)

        if (cropId != -1) {
            val crop = cropDAO.getCropById(cropId)
            if (crop != null) {
                editTxtCropName.setText(crop.name)
                editTxtMaxTemp.setText(crop.maxTemperature.toString())
                editTxtMinTemp.setText(crop.minTemperature.toString())
                editTxtMinHum.setText(crop.minHumidity.toString())
                editTxtMaxHum.setText(crop.maxHumidity.toString())
                editTxtLatitude.setText(crop.latitude.toString())
                editTxtLongitude.setText(crop.longitude.toString())
                editTxtComments.setText(crop.comments)
                editTxtPlantingDate.setText(crop.plantingDate)
                when (crop.type) {
                    "Cereal" -> rdGroupCropType.check(R.id.rdBtnCereal)
                    "Vegetable" -> rdGroupCropType.check(R.id.rdBtnVegetable)
                    "Fruit" -> rdGroupCropType.check(R.id.rdBtnFruit)
                }
            }
        }

        editTxtPlantingDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, dayOfMonth)
                    plantingDate = SimpleDateFormat(
                        "dd/MM/yyyy",
                        Locale.getDefault()
                    ).format(selectedDate.time)
                    editTxtPlantingDate.setText(plantingDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        btnSave.setOnClickListener {
            val name = editTxtCropName.text.toString()
            val minTemp = editTxtMinTemp.text.toString().toDoubleOrNull() ?: 0.0
            val maxTemp = editTxtMaxTemp.text.toString().toDoubleOrNull() ?: 0.0
            val minHum = editTxtMinHum.text.toString().toDoubleOrNull() ?: 0.0
            val maxHum = editTxtMaxHum.text.toString().toDoubleOrNull() ?: 0.0
            val latitude = editTxtLatitude.text.toString().toDoubleOrNull() ?: 0.0
            val longitude = editTxtLongitude.text.toString().toDoubleOrNull() ?: 0.0
            val plantingDate = editTxtPlantingDate.text.toString()
            val comments = if (editTxtComments.text.toString()
                    .isBlank()
            ) null else editTxtComments.text.toString()

            val type = when (rdGroupCropType.checkedRadioButtonId) {
                R.id.rdBtnCereal -> "Cereal"
                R.id.rdBtnVegetable -> "Vegetable"
                R.id.rdBtnFruit -> "Fruit"
                else -> ""
            }

            // TODO: GPS from latitude and longitude

            val crop = Crop(
                cropId,
                name,
                type,
                minTemp,
                maxTemp,
                minHum,
                maxHum,
                latitude,
                longitude,
                plantingDate,
                "",
                comments.toString()
            )
            if (cropId == -1) {
                cropDAO.insertCrop(crop)
            } else {
                cropDAO.updateCrop(crop)
            }

            setResult(RESULT_OK)
            finish()
        }

        btnCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}