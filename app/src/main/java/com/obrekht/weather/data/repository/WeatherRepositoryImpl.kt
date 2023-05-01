package com.obrekht.weather.data.repository

import com.obrekht.weather.data.api.RetrofitClient.weatherApi
import com.obrekht.weather.data.model.response.CurrentWeatherResponse

class WeatherRepositoryImpl : WeatherRepository {
    override suspend fun getCurrentWeather(latitude: Double, longitude: Double): CurrentWeatherResponse? {
        return weatherApi.getCurrentWeather(latitude, longitude, "metric", "en").body()
    }
}
