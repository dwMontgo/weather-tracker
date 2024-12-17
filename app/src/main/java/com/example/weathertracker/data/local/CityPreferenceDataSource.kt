package com.example.weathertracker.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CityPreferenceDataSource(private val dataStore: DataStore<Preferences>) {

    private val CITY_KEY = stringPreferencesKey("city_name")

    val savedCityFlow: Flow<String?> = dataStore.data.map { prefs ->
        prefs[CITY_KEY]
    }

    suspend fun saveCity(city: String) {
        dataStore.edit { prefs ->
            prefs[CITY_KEY] = city
        }
    }
}
