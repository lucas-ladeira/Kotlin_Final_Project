package com.example.projetfinal

data class Crop(
    var id: Int = 0,
    var name: String = "",
    var type: String = "",
    var maxTemperature: Double = 0.0,
    var minTemperature: Double = 0.0,
    var maxHumidity: Double = 0.0,
    var minHumidity: Double = 0.0,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var dateChoice: String = "",
    var address: String = "",
    var comments: String = ""
)