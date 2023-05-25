package com.example.meteo2

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = "YOUR_API_KEY",
        @Query("units") units: String = "metric"
    ): WeatherDataResponse
}

data class WeatherDataResponse(
    val name: String,
    val main: WeatherMainData,
    val clouds: WeatherCloudsData
)

data class WeatherMainData(
    val temp: Double
)

data class WeatherCloudsData(
    val all: Double
)