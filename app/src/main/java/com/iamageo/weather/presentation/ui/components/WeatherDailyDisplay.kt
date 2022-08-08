package com.iamageo.weather.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iamageo.weather.domain.weather.WeatherType

@Composable
fun DailyWeatherDisplay(
    dayName: String,
    backgroundColor: Color,
    weatherType: WeatherType,
    maxTemperature: Double,
    minTemperature: Double
) {
    Card(
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(16.dp, 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth().background(backgroundColor)
                .padding(horizontal = 16.dp, vertical = 24.dp),
        ) {
            Text(
                text = dayName,
                modifier = Modifier
                    .widthIn(100.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                )
            )
            Image(
                painter = painterResource(id = weatherType.iconRes),
                contentDescription = weatherType.weatherDesc,
                modifier = Modifier.size(34.dp)
            )
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "${maxTemperature.toInt()}°",
                    style = TextStyle(
                        fontSize = 22.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                    ),
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "${minTemperature.toInt()}°",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                    ),
                )
            }
        }
    }
}
