package com.example.weathertracker.data

import android.util.Log
import com.example.weathertracker.data.remote.WeatherApiService
import com.example.weathertracker.data.remote.toDomainModel
import com.example.weathertracker.domain.model.Weather
import com.example.weathertracker.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val apiService: WeatherApiService
) : WeatherRepository {
    override suspend fun getCurrentWeather(city: String, apiKey: String): Result<Weather> {
        return try {
            val response = apiService.getCurrentWeather(apiKey, city)
            Result.success(response.toDomainModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
