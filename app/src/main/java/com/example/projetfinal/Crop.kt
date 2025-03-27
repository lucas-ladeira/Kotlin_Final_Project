package com.example.projetfinal

data class Crop(
    val id: Long = 0,
    val name: String = "",
    val type: String = "",
    val maxTemperature: Double = 0.0,
    val minTemperature: Double = 0.0,
    val maxHumidity: Double = 0.0,
    val minHumidity: Double = 0.0,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val comments: String = ""
)