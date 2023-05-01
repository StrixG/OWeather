package com.obrekht.weather.data.repository

import com.obrekht.weather.data.model.response.CurrentWeatherResponse

interface WeatherRepository {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double): CurrentWeatherResponse?
}
