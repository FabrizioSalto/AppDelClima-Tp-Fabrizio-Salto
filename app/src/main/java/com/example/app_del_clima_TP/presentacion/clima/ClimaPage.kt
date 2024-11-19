package com.example.app_del_clima_TP.presentacion.clima

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.app_del_clima_TP.presentacion.PageView
import com.example.app_del_clima_TP.presentacion.clima.actual.WeatherView
import com.example.app_del_clima_TP.presentacion.clima.pronostico.ForecastView
import com.example.app_del_clima_TP.presentacion.clima.pronostico.ForecastViewModel
import com.example.app_del_clima_TP.presentacion.clima.pronostico.ForecastViewModelFactory
import com.example.app_del_clima_TP.presentacion.clima.actual.WeatherState
import com.example.app_del_clima_TP.presentacion.clima.actual.WeatherViewModel
import com.example.app_del_clima_TP.presentacion.clima.actual.WeatherViewModelFactory
import com.example.app_del_clima_TP.repository.Repository
import com.example.app_del_clima_TP.repository.models.WeatherDTO
import com.example.app_del_clima_TP.router.Router

@Composable
fun WeatherPage(
    navHostController: NavHostController,
    lat: Float,
    lon: Float,
    name: String
) {
    val weatherViewModel : WeatherViewModel = viewModel(
        factory = WeatherViewModelFactory(
            repository = Repository(),
            router = Router(navHostController),
            lat = lat,
            lon = lon,
            name = name
        )
    )

    val forecastViewModel : ForecastViewModel = viewModel(
        factory = ForecastViewModelFactory(
            repository = Repository(),
            router = Router(navHostController),
            name = name
        )
    )

    PageView(
        model = when (val state = weatherViewModel.state) {
            is WeatherState.Success -> state.weatherDTO
            else -> WeatherDTO()
        }
    ) {
        WeatherView(
            state = weatherViewModel.state,
            execute = { intention ->
                weatherViewModel.execute(intention)
            }
        )
        ForecastView(
            state = forecastViewModel.state,
            execute = { intention ->
                forecastViewModel.execute(intention)
            }
        )
    }

}