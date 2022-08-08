package com.iamageo.weather

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.iamageo.weather.presentation.theme.WeatherTheme
import com.iamageo.weather.presentation.ui.WeatherViewModel
import com.iamageo.weather.presentation.ui.components.LocationDisplay
import com.iamageo.weather.presentation.ui.components.WeatherCard
import com.iamageo.weather.presentation.ui.components.WeatherForecast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
        setContent {
            WeatherTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        LocationDisplay(location = "Location")
                        WeatherCard(
                            state = viewModel.state,
                            backgroundColor = MaterialTheme.colors.primary
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Column(
                            modifier = Modifier
                                .background(Color.White)
                        ) {
                            WeatherForecast(state = viewModel.state, day = 0)
                            WeatherForecast(state = viewModel.state, day = 1)
                            WeatherForecast(state = viewModel.state, day = 2)
                            WeatherForecast(state = viewModel.state, day = 3)
                            WeatherForecast(state = viewModel.state, day = 4)
                            WeatherForecast(state = viewModel.state, day = 5)
                            WeatherForecast(state = viewModel.state, day = 6)
                            Spacer(modifier = Modifier.height(20.dp))
                        }

                    }
                    if (viewModel.state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    viewModel.state.error?.let { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}
