package com.example.weathertracker.di

import com.example.weathertracker.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get()) }
}
