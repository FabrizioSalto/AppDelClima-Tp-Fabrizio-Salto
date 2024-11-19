package com.example.app_del_clima_TP.presentacion.clima.actual

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.app_del_clima_TP.repository.IRepository
import com.example.app_del_clima_TP.repository.models.WeatherDTO
import com.example.app_del_clima_TP.router.Router
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    val repository: IRepository,
    val router: Router,
    val lat: Float,
    val lon : Float,
    val name: String
) : ViewModel() {
    var state by mutableStateOf<WeatherState>(WeatherState.Empty)
    private val _weather = MutableStateFlow(WeatherDTO())
    val weather: StateFlow<WeatherDTO> get() = _weather

    fun execute(intention: WeatherIntention) {
        when(intention) {
            is WeatherIntention.Update -> loadWeather()
        }
    }

    private fun loadWeather() {
        state = WeatherState.Loading

        viewModelScope.launch {
            try {
                val weather = repository.getWeather(lat = lat, lon = lon)
                _weather.value = weather
                state = WeatherState.Success(weather)
            } catch (exception: Exception) {
                state = WeatherState.Error(exception.localizedMessage ?: "Unknown error")
            }
        }
    }
}

class WeatherViewModelFactory(
    private val repository: IRepository,
    private val router: Router,
    private val lat: Float,
    private val lon: Float,
    private val name: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override  fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(repository, router, lat, lon, name) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}