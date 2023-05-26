package com.example.meteo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meteo2.databinding.ActivityProgressBarBinding

class ProgressBarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProgressBarBinding
    private lateinit var viewModel: MyViewModel
    private lateinit var viewModelFactory: MyViewModelFactory
    private lateinit var adapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = RetrofitClientInstance.getRetrofitInstance()
        val weatherService = retrofit.create(WeatherApi::class.java)
        val weatherRepository = WeatherRepository(weatherService)

        viewModelFactory = MyViewModelFactory(weatherRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MyViewModel::class.java]
        binding = ActivityProgressBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}