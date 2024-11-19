package com.example.app_del_clima_TP.presentacion.clima.actual

import com.example.app_del_clima_TP.repository.models.WeatherDTO

sealed class WeatherState {
    data object Empty : WeatherState()
    data object Loading : WeatherState()
    data class Success(val weatherDTO: WeatherDTO) : WeatherState()
    data class Error(val message: String) : WeatherState()
}
