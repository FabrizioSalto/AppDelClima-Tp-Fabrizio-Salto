package com.example.app_del_clima_TP

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app_del_clima_TP.presentacion.clima.WeatherPage
import com.example.app_del_clima_TP.presentacion.ciudad.CityPage
import com.example.app_del_clima_TP.router.NavTarget

@Composable
fun MainPage() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = NavTarget.Cities.id
    ) {

        composable(
            route = NavTarget.Cities.id
        ) {
            CityPage(navHostController)
        }

        composable(
            route = "weather?lat={lat}&lon={lon}&name={name}",
            arguments = listOf(
                navArgument("lat") { type = NavType.FloatType },
                navArgument("lon") { type = NavType.FloatType },
                navArgument("name") { type = NavType.StringType }
            )
        ) {
            val lat = it.arguments?.getFloat("lat") ?: 0.0f
            val lon = it.arguments?.getFloat("lon") ?: 0.0f
            val name = it.arguments?.getString("name") ?: ""
            WeatherPage(navHostController, lat, lon, name)
        }

    }
}