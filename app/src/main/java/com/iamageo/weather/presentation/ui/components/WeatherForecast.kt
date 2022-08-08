package com.iamageo.weather.presentation.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iamageo.weather.domain.weather.WeatherType
import com.iamageo.weather.presentation.ui.WeatherState
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForecast(
    state: WeatherState,
    day: Int
) {
    val weatherDataList = state.weatherInfo?.weatherDataPerDay

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

    val dayName = when (day) {
        0 -> "Today"
        1 -> "Tomorrow"
        else -> {
            LocalDateTime
                .now()
                .plusDays(day.toLong())
                .dayOfWeek
                .toString()
                .lowercase()
                .replaceFirstChar {
                    it.uppercase()
                }
        }
    }

    if (day >= 1) {
        val weatherTypeList = ArrayList<Int>()
        var maxTemperature = 0.0
        var minTemperature = 60.0
        weatherDataList?.get(day)
            ?.map {
                weatherTypeList.add(it.weatherType.weatherCode)
                maxTemperature = if (maxTemperature > it.temperatureCelcius){
                    maxTemperature
                } else {
                    it.temperatureCelcius
                }
                minTemperature = if (minTemperature < it.temperatureCelcius){
                    minTemperature
                } else {
                    it.temperatureCelcius
                }
            }
            ?.toList()
        val weatherAverageCode = weatherTypeList
            .groupingBy { it }
            .eachCount()
            .maxByOrNull { it.value }
            ?.key
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Divider(
                color = Color.Gray
                    .copy(
                        alpha = 0.5f
                    ),
                thickness = 0.2.dp
            )
            DailyWeatherDisplay(
                dayName = dayName,
                weatherType = WeatherType.fromWMO(weatherAverageCode ?: 0),
                maxTemperature = maxTemperature,
                minTemperature = minTemperature,
                backgroundColor = listOfColors[(0..17).random()]
            )
        }
    } else {
        val localTime = LocalDateTime.now()
        localTime.format(DateTimeFormatter.ISO_DATE_TIME)
        weatherDataList?.get(day)
            ?.filter { filterData ->
                filterData.time.hour >= localTime.hour - 1
                        && filterData.time.dayOfMonth == localTime.dayOfMonth
            }
            ?.toList()
            ?.let { data ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = dayName,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        modifier = Modifier
                            .padding(16.dp)
                    )
                    Divider(
                        color = Color.Gray
                            .copy(
                                alpha = 0.5f
                            ),
                        thickness = 0.2.dp
                    )
                    LazyRow(
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        content = {
                            items(data) { weatherData ->
                                HourlyWeatherDisplay(weatherData = weatherData)
                            }
                        }
                    )
                }
            }
    }
}