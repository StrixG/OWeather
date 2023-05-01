package com.obrekht.weather.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherResponse(
    val weather: List<Weather>,
    val main: Main,
    val name: String
)

@Serializable
data class Weather(
    val id: Int,
    val main: String,
    val icon: String
)

@Serializable
data class Main(
    val temp: Double,
    val feels_like: Double
)