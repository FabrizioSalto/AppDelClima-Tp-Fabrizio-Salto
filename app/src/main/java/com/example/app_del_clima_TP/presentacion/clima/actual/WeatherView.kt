package com.example.app_del_clima_TP.presentacion.clima.actual

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.app_del_clima_TP.iconos.IconManager
import com.example.app_del_clima_TP.iconos.IconSize
import com.example.app_del_clima_TP.presentacion.GetIcon
import com.example.app_del_clima_TP.presentacion.events.EmptyView
import com.example.app_del_clima_TP.presentacion.events.ErrorView
import com.example.app_del_clima_TP.presentacion.events.LoadingSpinner
import com.example.app_del_clima_TP.repository.models.WeatherDTO
import com.example.app_del_clima_TP.ui.theme.displayFontFamily

@Composable
private fun TextDisplay(text: String, size: TextUnit, modifier: Modifier ?= Modifier) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        fontFamily = displayFontFamily,
        fontSize = size,
        modifier = modifier ?: Modifier
    )
}

@Composable
fun WeatherView(
    state: WeatherState,
    execute: (WeatherIntention) -> Unit
) {
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        execute(WeatherIntention.Update)
    }
    when(state) {
        is WeatherState.Loading -> LoadingSpinner()
        is WeatherState.Error ->  ErrorView(state.message)
        is WeatherState.Success -> SuccessfullView(state.weatherDTO)
        is WeatherState.Empty -> EmptyView()
    }
}

@Composable
fun SuccessfullView(currentWeather: WeatherDTO) {
    val modifier: Modifier = Modifier
    Column( modifier = modifier.padding(horizontal = 10.dp)) {
        CreateTempBox(currentWeather.main.temp.toString())
        Spacer(modifier.weight(0.5f))
        WeatherInfoRow(currentWeather.name, currentWeather.weather[0].main)
    }
}

@Composable
private fun CreateTempBox(temperature: String) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "$temperature ºc",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = MaterialTheme.typography.displayLarge.fontSize,
                    fontFamily = FontFamily.Serif, // Cambiar por la familia deseada
                    fontWeight = FontWeight.Bold, // Cambiar el peso si es necesario
                    color = MaterialTheme.colorScheme.primary // Cambiar el color si es necesario
                )
            )
        }
    }
}

@Composable
fun WeatherInfoRow(cityName: String, weatherDescription: String) {
    val modifier: Modifier = Modifier
    Row(
        verticalAlignment = Alignment.CenterVertically


    ) {

        Spacer(modifier.weight(0.5f))
        Column(
            modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            Text(
                text = weatherDescription,
                color = MaterialTheme.colorScheme.primary,
            )

            TextDisplay(cityName, MaterialTheme.typography.titleLarge.fontSize)
            Spacer(Modifier.height(5.dp))

        }

        Box(
            modifier
                .weight(0.5f)
                .padding(end = 10.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}






