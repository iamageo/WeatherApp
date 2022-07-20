package com.iamageo.weather.presentation.ui

import com.iamageo.weather.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo ?= null,
    val isLoading: Boolean = false,
    val error: String? = null
)