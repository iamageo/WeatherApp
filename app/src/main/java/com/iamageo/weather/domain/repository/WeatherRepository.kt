package com.iamageo.weather.domain.repository

import com.iamageo.weather.domain.util.Resource
import com.iamageo.weather.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWetherData(lat: Double, long: Double): Resource<WeatherInfo>
}