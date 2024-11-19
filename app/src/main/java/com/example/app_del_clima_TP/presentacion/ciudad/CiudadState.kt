package com.example.app_del_clima_TP.presentacion.ciudad
import com.example.app_del_clima_TP.repository.models.Ciudad

sealed class CiudadState {
    data object Empty : CiudadState()
    data object Loading : CiudadState()
    data class Success(val cities: List<Ciudad>) : CiudadState()
    data class Error(val message: String) : CiudadState()
}
