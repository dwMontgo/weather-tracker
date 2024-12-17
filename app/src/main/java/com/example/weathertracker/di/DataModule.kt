package com.example.weathertracker.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.weathertracker.data.WeatherRepositoryImpl
import com.example.weathertracker.data.local.CityPreferenceDataSource
import com.example.weathertracker.domain.repository.WeatherRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private val Context.dataStore by preferencesDataStore(name = "settings")

val dataModule = module {
    single { androidContext().dataStore }
    single { CityPreferenceDataSource(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}
