package com.example.weathertracker.domain.usecase

import com.example.weathertracker.domain.model.Weather
import com.example.weathertracker.domain.repository.WeatherRepository

class GetCurrentWeatherUseCase(
    private val repository: WeatherRepository,
    private val apiKey: String
) {
    suspend operator fun invoke(city: String): Result<Weather> {
        return repository.getCurrentWeather(city, apiKey)
    }
}
