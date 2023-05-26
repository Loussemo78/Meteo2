package com.example.meteo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.meteo2.databinding.ActivityProgressBarBinding

class ProgressBarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProgressBarBinding
    private lateinit var viewModel: MyViewModel
    private lateinit var viewModelFactory: MyViewModelFactory
    private lateinit var adapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar)
    }
}