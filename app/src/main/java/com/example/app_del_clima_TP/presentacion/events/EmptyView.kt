package com.example.app_del_clima_TP.presentacion.events

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_del_clima_TP.ui.theme.AppTheme
import com.example.app_del_clima_TP.iconos.IconManager


@Composable
fun EmptyView() {
    EventView {
        EventImage(IconManager.emptyIcon)
        Text(
            text = "No hay nada aquí.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            fontSize = 37.sp
        )
        Spacer(Modifier.size(20.dp))
        Text(
            text = "Prueba buscando una ciudad!",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 21.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyViewPreview() {
    AppTheme {
        EmptyView()
    }
}