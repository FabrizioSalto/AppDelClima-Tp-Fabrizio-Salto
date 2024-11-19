package com.example.app_del_clima_TP.presentacion.clima.actual

sealed class WeatherIntention {
    data object Update: WeatherIntention()
}