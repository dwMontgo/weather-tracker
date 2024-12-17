package com.example.weathertracker.domain.usecase

import com.example.weathertracker.data.local.CityPreferenceDataSource

class SaveCityUseCase(private val cityDataSource: CityPreferenceDataSource) {
    suspend operator fun invoke(city: String) {
        cityDataSource.saveCity(city)
    }
}