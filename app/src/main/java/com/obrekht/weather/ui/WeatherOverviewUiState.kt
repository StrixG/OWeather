package com.obrekht.weather.ui

data class WeatherOverviewUiState(
    val locationName: String,
    val temperature: Int,
    val feelsLikeTemperature: Int,
    val weatherName: String
)