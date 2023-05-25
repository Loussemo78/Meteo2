package com.example.meteo2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyViewModelFactory(private val api: WeatherApi): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(WeatherRepository(api)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}