package com.example.weathertracker.data.remote

import com.example.weathertracker.domain.model.Weather

fun WeatherApiResponse.toDomainModel(): Weather {
    return Weather(
        cityName = location.name,
        temperature = current.temp_c,
        feelsLike = current.feelslike_c,
        conditionText = current.condition.text,
        iconUrl = "https:${current.condition.icon}",
        humidity = current.humidity,
        uvIndex = current.uv
    )
}
