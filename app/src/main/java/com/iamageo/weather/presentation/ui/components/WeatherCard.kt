package com.iamageo.weather.presentation.ui.components


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iamageo.weather.R
import com.iamageo.weather.presentation.ui.WeatherState
import kotlin.math.roundToInt


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        Column(
            modifier = Modifier.fillMaxWidth().padding(0.dp,16.dp,0.dp,0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Today",
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center,
            )
        }
        Card(
            backgroundColor = backgroundColor,
            shape = RoundedCornerShape(20.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${data.temperatureCelcius}ºC",
                        fontSize = 50.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = data.weatherType.weatherDesc, fontSize = 20.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(32.dp))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column() {
                        WeatherDataDisplay(
                            value = data.humidity.roundToInt(),
                            unit = "%",
                            icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                            iconTint = Color.White,
                            textStyle = TextStyle(color = Color.White)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        WeatherDataDisplay(
                            value = data.windSpeed.roundToInt(),
                            unit = "km/h",
                            icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                            iconTint = Color.White,
                            textStyle = TextStyle(color = Color.White)
                        )
                    }
                    Column() {
                        Image(
                            painter = painterResource(id = data.weatherType.iconRes),
                            contentDescription = null,
                            modifier = Modifier.width(100.dp)
                        )
                    }

                }
            }
        }

    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun myCardPreview() {
    WeatherCard(state = WeatherState(weatherInfo = null), backgroundColor = Color.Black)
}