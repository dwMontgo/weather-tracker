package com.example.weathertracker.di

import com.example.weathertracker.domain.usecase.GetCurrentWeatherUseCase
import com.example.weathertracker.domain.usecase.GetSavedCityUseCase
import com.example.weathertracker.domain.usecase.SaveCityUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetSavedCityUseCase(get()) }
    factory { SaveCityUseCase(get()) }
    factory { GetCurrentWeatherUseCase(get(), get()) }
}
