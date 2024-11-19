package com.example.app_del_clima_TP.presentacion.clima.pronostico

sealed class PronosticoIntention {
    data object Update: PronosticoIntention()
}