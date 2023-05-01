package com.obrekht.weather.data.api

import com.obrekht.weather.data.model.response.CurrentWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val weatherPath = "data/2.5"

interface OpenWeatherApi {
    @GET("$weatherPath/weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String,
        @Query("lang") languageCode: String
    ): Response<CurrentWeatherResponse>
}