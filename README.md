# WeatherTracker

A simple Android application that allows users to search and view current weather conditions for a given city. The app uses the [WeatherAPI](https://www.weatherapi.com/) service to fetch the latest weather data.

## Features

- Search for any city and view current weather conditions.
- Display temperature, humidity, UV index, and a visual weather icon.
- Save a city and automatically display its current weather on subsequent launches.

## API Key Setup

To run this application, you need a WeatherAPI key:

1. Sign up at [WeatherAPI](https://www.weatherapi.com/) and obtain an API key.
2. Open `com.example.weathertracker.di.apiModule` in your project.
3. Find the line:
   ```kotlin
   private const val API_KEY = "INSERT_API_KEY_HERE"
4. Replace "INSERT_API_KEY_HERE" with your actual API key
