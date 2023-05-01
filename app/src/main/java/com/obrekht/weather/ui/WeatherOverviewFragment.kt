package com.obrekht.weather.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.obrekht.weather.R
import com.obrekht.weather.databinding.FragmentWeatherOverviewBinding
import com.obrekht.weather.utils.viewBinding

class WeatherOverviewFragment : Fragment(R.layout.fragment_weather_overview) {
    private val viewModel by activityViewModels<WeatherViewModel>()
    private val binding by viewBinding(FragmentWeatherOverviewBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiState.observe(viewLifecycleOwner) {
            with(binding) {
                locationName.text = it.locationName
                currentTemperature.text = it.temperature.toString()
                feelsLikeTemperature.text = getString(R.string.feels_like, it.feelsLikeTemperature)
                weatherDescription.text = it.weatherName

                swipeRefresh.setOnRefreshListener {
                    // TODO: Update location and weather info
                }
            }
        }
    }
}