package com.example.app_del_clima_TP.presentacion.events

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app_del_clima_TP.ui.theme.AppTheme
import com.example.app_del_clima_TP.iconos.IconManager

@Composable
fun ErrorView(message: String) {
    EventView {
        EventImage(IconManager.errorIcon)
        EventText("Ocurrio un error, intente mas tarde.", "error")
        Spacer(modifier = Modifier.height(1.dp))
        EventText(message, "error")
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorViewPreview() {
    AppTheme {
        ErrorView("Error")
    }
}