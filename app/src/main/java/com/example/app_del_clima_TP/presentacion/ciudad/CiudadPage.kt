package com.example.app_del_clima_TP.presentacion.ciudad

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.app_del_clima_TP.presentacion.PageView

import com.example.app_del_clima_TP.repository.Repository
import com.example.app_del_clima_TP.repository.models.Ciudad
import com.example.app_del_clima_TP.router.Router

@Composable
fun CityPage(
    navHostController: NavHostController
) {
    val viewModel : CityViewModel = viewModel(
        factory = CityViewModelFactory(
            repository = Repository(),
            navigator = Router(navHostController)
        )
    )

    PageView<Ciudad> {
        CityView(
            state = viewModel.state,
            execute = { intention ->
                viewModel.execute(intention)
            }
        )
    }
}