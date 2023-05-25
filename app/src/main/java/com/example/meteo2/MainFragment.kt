package com.example.meteo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.meteo2.databinding.FragmentMainBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainFragment : Fragment() {



    private lateinit var viewModel: MyViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
         binding = FragmentMainBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
        val viewModelFactory = MyViewModelFactory(api)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fetchWeatherButton.setOnClickListener {
            val city = binding.editText.text.toString()
            viewModel.fetchWeatherData(city)
        }

        lifecycleScope.launch {
            viewModel.weatherData.collect { weatherData ->
                binding.weatherData.text = weatherData
            }
        }

        lifecycleScope.launch {
            viewModel.message.collect { message ->
                binding.message.text = message
            }
        }

        lifecycleScope.launch {
            viewModel.progressBar.collect { progressBar ->
                binding.progressBar.progress = progressBar
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
