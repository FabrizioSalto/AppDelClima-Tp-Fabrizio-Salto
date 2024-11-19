package com.example.app_del_clima_TP.presentacion.clima.pronostico

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.app_del_clima_TP.repository.IRepository
import com.example.app_del_clima_TP.router.Router
import kotlinx.coroutines.launch

class ForecastViewModel(
    val repository: IRepository,
    val router: Router,
    val name: String
) : ViewModel() {
    var state by mutableStateOf<PronosticoState>(PronosticoState.Empty)

    fun execute(intention: PronosticoIntention) {
        when(intention) {
            is PronosticoIntention.Update -> loadForecast()
        }
    }

    private fun loadForecast() {
        state = PronosticoState.Loading

        viewModelScope.launch {
            try {
                val forecast = repository.getForecast(name).filter { true }
                state = PronosticoState.Success(forecast)
            } catch (exception: Exception) {
                state = PronosticoState.Error(exception.localizedMessage ?: "Unknown error")
            }
        }
    }
}

class ForecastViewModelFactory(
    private val repository: IRepository,
    private val router: Router,
    private val name: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override  fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
            return ForecastViewModel(repository, router, name) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

