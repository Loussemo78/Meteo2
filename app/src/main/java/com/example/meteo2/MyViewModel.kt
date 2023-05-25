package com.example.meteo2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _weatherData = MutableStateFlow<String>("")
    val weatherData: StateFlow<String>
        get() = _weatherData

    private val _message = MutableStateFlow<String>("")
    val message: StateFlow<String>
        get() = _message

    private val _progressBar = MutableStateFlow<Int>(0)
    val progressBar: StateFlow<Int>
        get() = _progressBar

    fun fetchWeatherData(city: String) {
        viewModelScope.launch {
            _progressBar.value = 0
            _message.value = "Fetching weather data..."
            try {
                val weatherResponse = repository.getWeather(city)
                val weatherDescription = weatherResponse!!.weather[0].description
                val temperature = weatherResponse.main.temp.toString()
                _weatherData.value = "The weather in $city is $weatherDescription with a temperature of $temperature °C."
                _message.value = "Weather data fetched successfully!"
            } catch (e: Exception) {
                _message.value = "Failed to fetch weather data. Please try again later."
            } finally {
                _progressBar.value = 100
            }
        }
    }
}
