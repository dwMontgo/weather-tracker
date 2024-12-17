package com.example.weathertracker.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import coil.compose.AsyncImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathertracker.domain.model.Weather

@Composable
fun CurrentWeatherDisplay(weather: Weather) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(Modifier.height(40.dp))
        AsyncImage(
            model = weather.iconUrl,
            contentDescription = weather.conditionText,
            modifier = Modifier.size(160.dp)
        )
        // Reduced space between icon and city
        Spacer(Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                weather.cityName,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
            )
            Spacer(Modifier.width(2.dp))
            Icon(
                Icons.Default.LocationOn,
                contentDescription = "Location",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = "${weather.temperature.toInt()}°",
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 64.sp
            )
        )

        Spacer(Modifier.height(32.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(horizontal = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                WeatherInfoColumn(label = "Humidity", value = "${weather.humidity}%")
                WeatherInfoColumn(label = "UV", value = "${weather.uvIndex.toInt()}")
                WeatherInfoColumn(label = "Feels Like", value = "${weather.feelsLike.toInt()}°")
            }
        }
    }
}

@Composable
private fun WeatherInfoColumn(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Gray
            )
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}
