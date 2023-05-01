package com.obrekht.weather.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.obrekht.weather.data.repository.WeatherRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableLiveData<WeatherOverviewUiState>()
    val uiState: LiveData<WeatherOverviewUiState>
        get() = _uiState

    val locationState = MutableLiveData<Boolean>(false)

    private val repository = WeatherRepositoryImpl()

    fun updateWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            val responseBody = repository.getCurrentWeather(
                latitude,
                longitude,
            )

            responseBody?.let {
                _uiState.postValue(
                    WeatherOverviewUiState(
                        locationName = it.name,
                        temperature = it.main.temp.roundToInt(),
                        feelsLikeTemperature = it.main.feels_like.roundToInt(),
                        weatherName = it.weather.firstOrNull()?.main ?: "-"
                    )
                )
            }
        }
    }

    fun requestLocationUpdate() {
        locationState.value = true
    }

    fun locationUpdated() {
        locationState.value = false
    }
}