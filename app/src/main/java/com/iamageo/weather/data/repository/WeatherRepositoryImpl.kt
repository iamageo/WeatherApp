package com.iamageo.weather.data.repository

import com.iamageo.weather.data.remote.WeatherApi
import com.iamageo.weather.domain.repository.WeatherRepository
import com.iamageo.weather.domain.util.Resource
import com.iamageo.weather.domain.weather.WeatherData
import java.lang.Exception
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api : WeatherApi
): WeatherRepository {

    override suspend fun getWetherData(lat: Double, long: Double): Resource<WeatherData> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat, long
                ).toWeahterInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

}