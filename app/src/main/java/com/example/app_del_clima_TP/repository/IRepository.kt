package com.example.app_del_clima_TP.repository

import com.example.app_del_clima_TP.repository.models.Ciudad
import com.example.app_del_clima_TP.repository.models.ListForecast
import com.example.app_del_clima_TP.repository.models.WeatherDTO

interface IRepository {
    suspend fun getCity(city: String): List<Ciudad>
    suspend fun getWeather(lat: Float, lon: Float): WeatherDTO
    suspend fun getForecast(name: String): List<ListForecast>
}