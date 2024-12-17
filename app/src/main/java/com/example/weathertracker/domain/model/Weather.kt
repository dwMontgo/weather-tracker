package com.example.weathertracker.domain.model

data class Weather(
    val cityName: String,
    val temperature: Double,
    val feelsLike: Double,
    val conditionText: String,
    val iconUrl: String,
    val humidity: Int,
    val uvIndex: Double
)
