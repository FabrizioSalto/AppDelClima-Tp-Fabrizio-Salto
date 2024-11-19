package com.example.app_del_clima_TP.presentacion.ciudad

import com.example.app_del_clima_TP.repository.models.Ciudad

sealed class CiudadIntention {
    data class Search(val cityName: String) : CiudadIntention()
    data class Select(val ciudad: Ciudad) : CiudadIntention()
}