package com.example.meteo2

class WeatherRepository(private val api: WeatherApi) {

    suspend fun getWeather(city: String): WeatherDataResponse ? {
        return try {
            api.getWeather(city)
        } catch (e: Exception) {
            null
        }
    }
}
