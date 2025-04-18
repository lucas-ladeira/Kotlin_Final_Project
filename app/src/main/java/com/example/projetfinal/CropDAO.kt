package com.example.projetfinal

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CropDAO(private val db: SQLiteDatabase) {
    private val firebaseDatabase: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("crops")

    fun insertCrop(crop: Crop): Long {
        val values = ContentValues().apply {
            put(CropDatabaseHelper.COLUMN_NAME, crop.name)
            put(CropDatabaseHelper.COLUMN_TYPE, crop.type)
            put(CropDatabaseHelper.COLUMN_MIN_TEMPERATURE, crop.minTemperature)
            put(CropDatabaseHelper.COLUMN_MAX_TEMPERATURE, crop.maxTemperature)
            put(CropDatabaseHelper.COLUMN_MIN_HUMIDITY, crop.minHumidity)
            put(CropDatabaseHelper.COLUMN_MAX_HUMIDITY, crop.maxHumidity)
            put(CropDatabaseHelper.COLUMN_LATITUDE, crop.latitude)
            put(CropDatabaseHelper.COLUMN_LONGITUDE, crop.longitude)
            put(CropDatabaseHelper.COLUMN_DATE_CHOICE, crop.plantingDate)
            put(CropDatabaseHelper.COLUMN_ADDRESS, crop.address)
            put(CropDatabaseHelper.COLUMN_COMMENTS, crop.comments)
        }
        val id = db.insert(CropDatabaseHelper.TABLE_CROPS, null, values)
        if (id != -1L) {
            crop.id = id.toInt()
            firebaseDatabase.child(crop.id.toString()).setValue(crop)
        }
        return id
    }

    fun updateCrop(crop: Crop): Int {
        val values = ContentValues().apply {
            put(CropDatabaseHelper.COLUMN_NAME, crop.name)
            put(CropDatabaseHelper.COLUMN_TYPE, crop.type)
            put(CropDatabaseHelper.COLUMN_MIN_TEMPERATURE, crop.minTemperature)
            put(CropDatabaseHelper.COLUMN_MAX_TEMPERATURE, crop.maxTemperature)
            put(CropDatabaseHelper.COLUMN_MIN_HUMIDITY, crop.minHumidity)
            put(CropDatabaseHelper.COLUMN_MAX_HUMIDITY, crop.maxHumidity)
            put(CropDatabaseHelper.COLUMN_LATITUDE, crop.latitude)
            put(CropDatabaseHelper.COLUMN_LONGITUDE, crop.longitude)
            put(CropDatabaseHelper.COLUMN_DATE_CHOICE, crop.plantingDate)
            put(CropDatabaseHelper.COLUMN_ADDRESS, crop.address)
            put(CropDatabaseHelper.COLUMN_COMMENTS, crop.comments)
        }
        val rowsAffected = db.update(
            CropDatabaseHelper.TABLE_CROPS,
            values,
            "${CropDatabaseHelper.COLUMN_ID} = ?",
            arrayOf(crop.id.toString())
        )
        if (rowsAffected > 0) {
            firebaseDatabase.child(crop.id.toString()).setValue(crop)
        }
        return rowsAffected
    }

    fun getAllCrops(): List<Crop> {
        val crops = mutableListOf<Crop>()
        val cursor: Cursor = db.query(
            CropDatabaseHelper.TABLE_CROPS,
            null, null, null, null, null, null
        )
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_ID))
                val name =
                    cursor.getString(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_NAME))
                val type =
                    cursor.getString(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_TYPE))
                val minTemperature =
                    cursor.getDouble(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_MIN_TEMPERATURE))
                val maxTemperature =
                    cursor.getDouble(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_MAX_TEMPERATURE))
                val minHumidity =
                    cursor.getDouble(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_MIN_HUMIDITY))
                val maxHumidity =
                    cursor.getDouble(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_MAX_HUMIDITY))
                val latitude =
                    cursor.getDouble(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_LATITUDE))
                val longitude =
                    cursor.getDouble(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_LONGITUDE))
                val dateChoice =
                    cursor.getString(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_DATE_CHOICE))
                val address =
                    cursor.getString(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_ADDRESS))
                val comments =
                    cursor.getString(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_COMMENTS))
                val crop = Crop(
                    id,
                    name,
                    type,
                    minTemperature,
                    maxTemperature,
                    minHumidity,
                    maxHumidity,
                    latitude,
                    longitude,
                    dateChoice,
                    address,
                    comments
                )
                crops.add(crop)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return crops
    }

    fun getCropById(id: Int): Crop? {
        val cursor: Cursor = db.query(
            CropDatabaseHelper.TABLE_CROPS,
            null,
            "${CropDatabaseHelper.COLUMN_ID} = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        if (cursor.moveToFirst()) {
            val name =
                cursor.getString(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_NAME))
            val type =
                cursor.getString(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_TYPE))
            val minTemperature =
                cursor.getDouble(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_MIN_TEMPERATURE))
            val maxTemperature =
                cursor.getDouble(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_MAX_TEMPERATURE))
            val minHumidity =
                cursor.getDouble(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_MIN_HUMIDITY))
            val maxHumidity =
                cursor.getDouble(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_MAX_HUMIDITY))
            val latitude =
                cursor.getDouble(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_LATITUDE))
            val longitude =
                cursor.getDouble(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_LONGITUDE))
            val dateChoice =
                cursor.getString(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_DATE_CHOICE))
            val address =
                cursor.getString(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_ADDRESS))
            val comments =
                cursor.getString(cursor.getColumnIndexOrThrow(CropDatabaseHelper.COLUMN_COMMENTS))
            val crop = Crop(
                id,
                name,
                type,
                minTemperature,
                maxTemperature,
                minHumidity,
                maxHumidity,
                latitude,
                longitude,
                dateChoice,
                address,
                comments
            )
            cursor.close()
            return crop
        }
        cursor.close()
        return null
    }

    fun deleteCrop(id: Int): Int {
        val rowsDeleted = db.delete(
            CropDatabaseHelper.TABLE_CROPS,
            "${CropDatabaseHelper.COLUMN_ID} = ?",
            arrayOf(id.toString())
        )
        if (rowsDeleted > 0) {
            firebaseDatabase.child(id.toString()).removeValue()
        }
        return rowsDeleted
    }
}
