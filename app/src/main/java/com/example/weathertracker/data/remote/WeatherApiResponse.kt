package com.example.weathertracker.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherApiResponse(
    val location: LocationResponse,
    val current: CurrentResponse
)

@JsonClass(generateAdapter = true)
data class LocationResponse(
    val name: String,
    val region: String?,
    val country: String?,
    val lat: Double?,
    val lon: Double?,
    val tz_id: String?,
    val localtime_epoch: Long?,
    val localtime: String?
)

@JsonClass(generateAdapter = true)
data class CurrentResponse(
    @Json(name = "temp_c") val temp_c: Double,
    @Json(name = "feelslike_c") val feelslike_c: Double,
    val condition: ConditionResponse,
    val humidity: Int,
    val uv: Double
)

@JsonClass(generateAdapter = true)
data class ConditionResponse(
    val text: String,
    val icon: String,
    val code: Int
)
