package com.example.weathertracker

import android.app.Application
import com.example.weathertracker.di.apiModule
import com.example.weathertracker.di.dataModule
import com.example.weathertracker.di.domainModule
import com.example.weathertracker.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(apiModule, dataModule, domainModule, uiModule))
        }
    }
}