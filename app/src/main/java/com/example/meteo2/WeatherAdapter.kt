package com.example.meteo2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.meteo2.databinding.ItemWeatherDataBinding

class WeatherAdapter(private val weatherDataList: List<WeatherData>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWeatherDataBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherData = weatherDataList[position]
        holder.bind(weatherData)
    }

    override fun getItemCount(): Int {
        return weatherDataList.size
    }

    inner class ViewHolder(private val binding: ItemWeatherDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(weatherData: WeatherData) {
            // Bind the weather data to the view
            binding.cityNameTextView.text = weatherData.cityName
            binding.temperatureTextView.text = weatherData.temperature.toString()
            binding.cloudinessTextView.text = weatherData.cloudiness.toString()
        }
    }

}
