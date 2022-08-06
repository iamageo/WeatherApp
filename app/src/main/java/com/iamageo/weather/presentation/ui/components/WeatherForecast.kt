package com.iamageo.weather.presentation.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iamageo.weather.presentation.ui.WeatherState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier,
    day: Int?= 0
) {
    val listOfColors by lazy {
        listOf(
            Color(0xfff44336),
            Color(0xffe91e63),
            Color(0xff9c27b0),
            Color(0xff673ab7),
            Color(0xff3f51b5),
            Color(0xff2196f3),
            Color(0xff03a9f4),
            Color(0xff00bcd4),
            Color(0xff009688),
            Color(0xff4caf50),
            Color(0xff8bc34a),
            Color(0xffcddc39),
            Color(0xffffeb3b),
            Color(0xffffc107),
            Color(0xffff9800),
            Color(0xffff5722),
            Color(0xff795548),
            Color(0xff9e9e9e),
        )
    }
    state.weatherInfo?.weatherDataPerDay?.get(day)?.let { data ->
        Column(
            modifier = modifier
                .padding(horizontal = 8.dp)
        ) {

            HourlyWeatherDisplay(
                weatherData = data,
                modifier = Modifier
                    .height(100.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                backgroundColor = listOfColors[day!!]
            )
        }
    }
}
