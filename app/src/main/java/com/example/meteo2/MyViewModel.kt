package com.example.meteo2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _weatherDataList = MutableSharedFlow<WeatherDataResponse>()
    val weatherDataList: SharedFlow<WeatherDataResponse> = _weatherDataList

    suspend fun fetchWeatherData(cities: Array<String>) {
        for (city in cities) {
            try {
                val weatherData = repository.getWeather(city)
                if (weatherData != null) {
                    _weatherDataList.emit(weatherData)
                }
                delay(10000) // Delay for 10 seconds before making the next API call
            } catch (e: Exception) {
                // Handle any exceptions that may occur during the API call
            }
        }
    }

}
