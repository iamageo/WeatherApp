package com.iamageo.weather.domain.repository

import com.iamageo.weather.domain.util.Resource
import com.iamageo.weather.domain.weather.WeatherData

interface WeatherRepository {
    suspend fun getWetherData(lat: Long, long: Long): Resource<WeatherData>
}