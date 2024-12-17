package com.example.weathertracker.domain.repository

import com.example.weathertracker.domain.model.Weather

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String, apiKey: String): Result<Weather>
}
