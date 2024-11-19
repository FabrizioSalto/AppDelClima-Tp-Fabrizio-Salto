package com.example.app_del_clima_TP.iconos

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class IconModel(
    val name: String,
    val imageVector: ImageVector,
    val description: String,
    val tint: Color
)

object IconSize {
    val extraSmall = 26.dp
    val small = 38.dp
    val medium = 72.dp
    val large = 96.dp
    val extraLarge = 150.dp
}


object IconManager {
    val shareIcon = IconModel(
        name = "share",
        imageVector = Icons.Outlined.Share, // Usamos el ícono de compartir de Material Icons
        description = "Icono de compartir",
        tint = Color(0xFFE8EAED) // Color similar al ejemplo
    )

    val settingsIcon = IconModel(
        name = "settings",
        imageVector = Icons.Outlined.Settings, // Usamos el ícono de configuración de Material Icons
        description = "Icono de ajustes",
        tint = Color(0xFFE8EAED)
    )

    val windIcon = IconModel(
        name = "wind",
        imageVector = Icons.Outlined.Info, // Usamos el ícono de viento de Material Icons
        description = "Icono de viento",
        tint = Color(0xFFE8EAED)
    )

    val errorIcon = IconModel(
        name = "error",
        imageVector = Icons.Outlined.Warning, // Usamos el ícono de error de Material Icons
        description = "Icono de error",
        tint = Color(0xFFE8EAED)
    )

    val loadingIcon = IconModel(
        name = "loading",
        imageVector = Icons.Outlined.Refresh, // Usamos el ícono de carga de Material Icons
        description = "Icono de carga",
        tint = Color(0xFFE8EAED)
    )

    val emptyIcon = IconModel(
        name = "empty",
        imageVector = Icons.Outlined.Home, // Usamos el ícono de inicio de Material Icons
        description = "Icono de inicio",
        tint = Color(0xFFE8EAED) // Color similar al ejemplo
    )
}