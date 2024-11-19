package com.example.app_del_clima_TP.presentacion.ciudad
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.app_del_clima_TP.repository.IRepository
import com.example.app_del_clima_TP.repository.models.Ciudad
import com.example.app_del_clima_TP.router.NavTarget
import com.example.app_del_clima_TP.router.Router
import kotlinx.coroutines.launch

class CityViewModel(
    val repository: IRepository,
    private val navigator: Router
) : ViewModel() {
    var state by mutableStateOf<CiudadState>(CiudadState.Empty)
    private var cities: List<Ciudad> = emptyList()

    fun execute(intention: CiudadIntention) {
        when(intention) {
            is CiudadIntention.Search -> searchCity(intention.cityName)
            is CiudadIntention.Select -> selectCity(intention.ciudad)
        }
    }

    private fun searchCity(cityName: String) {
        state = CiudadState.Loading
        viewModelScope.launch {
            try {
                cities = repository.getCity(cityName)
                state = if(cities.isEmpty()) {
                    CiudadState.Empty
                } else {
                    CiudadState.Success(cities)
                }
            } catch (exception: Exception) {
                state = CiudadState.Error(exception.localizedMessage ?: "Unknown error")
            }
        }
    }

    private  fun selectCity(ciudad: Ciudad) {
        val route = NavTarget.Weather(ciudad.lat, ciudad.lon, ciudad.name)
        navigator.navigate(route)
    }
}

class CityViewModelFactory(
    private val repository: IRepository,
    private val navigator: Router
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            return CityViewModel(repository, navigator) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}