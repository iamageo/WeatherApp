package com.iamageo.weather.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.iamageo.weather.data.mappers.toWeatherInfo
import com.iamageo.weather.data.remote.WeatherApi
import com.iamageo.weather.domain.repository.WeatherRepository
import com.iamageo.weather.domain.util.Resource
import com.iamageo.weather.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWetherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

}