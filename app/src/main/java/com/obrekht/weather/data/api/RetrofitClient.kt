package com.obrekht.weather.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.obrekht.weather.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RetrofitClient {
    private const val BASE_URL = "https://api.openweathermap.org/"
    private const val API_KEY = BuildConfig.OPENWEATHER_API_KEY

    private val retrofit: Retrofit by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val url = it.request().url.newBuilder()
                    .addQueryParameter("appid", API_KEY)
                    .build()
                it.proceed(
                    it.request().newBuilder()
                        .url(url)
                        .build()
                )
            }
            .build()

        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val weatherApi: OpenWeatherApi by lazy {
        retrofit.create(OpenWeatherApi::class.java)
    }
}