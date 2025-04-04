package com.example.projetfinal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CropDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "crop_database.db"
        const val DATABASE_VERSION = 1
        const val TABLE_CROPS = "crops"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_TYPE = "type"
        const val COLUMN_MIN_TEMPERATURE = "min_temperature"
        const val COLUMN_MAX_TEMPERATURE = "max_temperature"
        const val COLUMN_MIN_HUMIDITY = "min_humidity"
        const val COLUMN_MAX_HUMIDITY = "max_humidity"
        const val COLUMN_LATITUDE = "latitude"
        const val COLUMN_LONGITUDE = "longitude"
        const val COLUMN_DATE_CHOICE = "date_choice"
        const val COLUMN_ADDRESS = "address"
        const val COLUMN_COMMENTS = "comments"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery="""
            CREATE TABLE $TABLE_CROPS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_TYPE TEXT,
                $COLUMN_MIN_TEMPERATURE REAL,
                $COLUMN_MAX_TEMPERATURE REAL,
                $COLUMN_MIN_HUMIDITY REAL,
                $COLUMN_MAX_HUMIDITY REAL,
                $COLUMN_LATITUDE REAL,
                $COLUMN_LONGITUDE REAL,
                $COLUMN_DATE_CHOICE TEXT,
                $COLUMN_ADDRESS TEXT,
                $COLUMN_COMMENTS TEXT
            )
        """.trimIndent()
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_CROPS")
        onCreate(db)
    }
}