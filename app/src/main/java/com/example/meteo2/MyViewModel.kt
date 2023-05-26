package com.example.meteo2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MyViewModel(private val repository: WeatherRepository) : ViewModel() {

    companion object {
        const val MESSAGE_DOWNLOADING = "Nous téléchargeons les données..."
        const val MESSAGE_ALMOST_FINISHED = "C'est presque fini..."
        const val MESSAGE_FEW_SECONDS = "Plus que quelques secondes avant d'avoir le résultat..."
    }

    private var currentMessageIndex = 0

    private val _weatherDataList = MutableSharedFlow<WeatherDataResponse>()
    val weatherDataList: SharedFlow<WeatherDataResponse> = _weatherDataList

    private val _currentMessage: MutableStateFlow<String> = MutableStateFlow("")
    val currentMessage: StateFlow<String> = _currentMessage


    suspend fun fetchWeatherData(cities: Array<String>) {
        viewModelScope.launch {
            val timerFlow = flow {
                while (true) {
                    emit(Unit)
                    delay(6000)
                }
            }
            val messageFlow = timerFlow.map { getCurrentMessage() }

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
            messageFlow.collect { message ->
                _currentMessage.value = message
            }
        }

    }

    private fun getCurrentMessage(): String {
        return when (currentMessageIndex) {
            0 -> MESSAGE_DOWNLOADING
            1 -> MESSAGE_ALMOST_FINISHED
            2 -> MESSAGE_FEW_SECONDS
            else -> ""
        }
    }
}