package com.example.app_del_clima_TP.presentacion.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.example.app_del_clima_TP.iconos.IconModel
import com.example.app_del_clima_TP.ui.theme.displayFontFamily

@Composable
fun EventView(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        content()
    }
}

@Composable
fun EventText(text: String, colorType: String = "default") {

    val colorScheme = when(colorType) {
        "error" -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.primary
    }

    Text(
        text,
        color = colorScheme,
        fontFamily = displayFontFamily,
        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
        fontWeight = MaterialTheme.typography.headlineLarge.fontWeight
    )
}

@Composable
fun EventImage(icon: IconModel? = null) {
    if (icon != null) {
        Icon(
            imageVector = icon.imageVector,
            contentDescription = icon.description,
            modifier = Modifier
                .padding(25.dp)
                .fillMaxWidth()
                .aspectRatio(1f)
                .alpha(0.9f),
            tint = MaterialTheme.colorScheme.primary
        )
    } else {
        Text("Icono no encontrado")
    }
}

