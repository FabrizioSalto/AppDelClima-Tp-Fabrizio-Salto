package com.example.app_del_clima_TP.presentacion.clima.pronostico

import com.example.app_del_clima_TP.repository.models.ListForecast

sealed class PronosticoState {
    data object Empty : PronosticoState()
    data object Loading : PronosticoState()
    data class Success(val forecast: List<ListForecast>) : PronosticoState()
    data class Error(val message: String) : PronosticoState()
}
