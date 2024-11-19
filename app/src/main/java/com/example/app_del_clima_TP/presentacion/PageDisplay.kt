package com.example.app_del_clima_TP.presentacion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.app_del_clima_TP.ui.theme.AppTheme
import com.example.app_del_clima_TP.iconos.IconManager
import com.example.app_del_clima_TP.iconos.IconModel
import com.example.app_del_clima_TP.iconos.IconSize
import com.example.app_del_clima_TP.repository.models.WeatherDTO


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> PageView(model: T? = null ,content: @Composable (Modifier) -> Unit) {
    val modifier: Modifier = Modifier
    val scrollState = rememberScrollState()

    Scaffold (
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        if(model != null && model is WeatherDTO) {

                            CreateShareButton(model)
                        }

                        Spacer(modifier = Modifier.weight(1f))
                        CreateButton(action = {  }) {
                            GetIcon(IconManager.settingsIcon, IconSize.extraSmall)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier
                .padding(paddingValues)
                .padding(top = 15.dp)
                .padding(horizontal = 25.dp)
                .verticalScroll(scrollState)
        ) {
            content(modifier)
        }
    }
}



@Composable


fun GetIcon(icon: IconModel? = null, size: Dp) {
    if (icon != null) {
        Icon(
            imageVector = icon.imageVector,
            contentDescription = icon.description,
            modifier = Modifier
                .width(size)
                .aspectRatio(1f),
            tint = MaterialTheme.colorScheme.primary
        )
    } else {
        Text("Icono no encontrado")
    }
}

@Composable
private fun CreateButton(action: () -> Unit, content: @Composable () -> Unit) {
    Button(
        onClick = {action()},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .padding(end = 8.dp)
    )
    { content() }
}

@Preview(showBackground = true)
@Composable
fun PageViewPreview() {
    AppTheme {
        PageView<WeatherDTO> {
            Text("Todo mal")
        }
    }
}

@Composable
private fun CreateShareButton(weather: WeatherDTO) {
    val context = LocalContext.current
    val shareManager = ShareManager(context)
    CreateButton(action = { shareManager.shareWeatherStats(weather) }) {
        GetIcon(IconManager.shareIcon, IconSize.extraSmall )
    }
}