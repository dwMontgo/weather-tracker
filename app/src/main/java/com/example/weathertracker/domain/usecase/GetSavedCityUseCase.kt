package com.example.weathertracker.domain.usecase

import com.example.weathertracker.data.local.CityPreferenceDataSource
import kotlinx.coroutines.flow.Flow

class GetSavedCityUseCase(private val cityDataSource: CityPreferenceDataSource) {
    operator fun invoke(): Flow<String?> = cityDataSource.savedCityFlow
}
