package com.iamageo.weather.presentation.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.iamageo.weather.domain.weather.WeatherData
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyWeatherDisplay(
    weatherData: List<WeatherData>,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
    backgroundColor: Color = MaterialTheme.colors.secondary
) {
    weatherData.get(0).let { data ->
        val formattedTime = remember(weatherData) {
            data.time.format(
                DateTimeFormatter.ofPattern("HH:mm")
            )
        }
        Card(
            backgroundColor = backgroundColor,
            shape = RoundedCornerShape(10.dp),
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = formattedTime,
                    color = Color.White
                )
                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.width(40.dp)
                )
                Text(
                    text = "${data.temperatureCelcius}ºC",
                    color = textColor,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

}
