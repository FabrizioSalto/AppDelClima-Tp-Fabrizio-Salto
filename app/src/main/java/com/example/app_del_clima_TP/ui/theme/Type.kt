package com.example.app_del_clima_TP.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import com.example.app_del_clima_TP.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val displayFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Inknut Antiqua"),
        fontProvider = provider
    )
)

enum class WindowSize { Compact, Medium, Expanded }

@Composable
fun getWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current
    return when {
        configuration.screenWidthDp < 600 -> WindowSize.Compact
        configuration.screenWidthDp < 840 -> WindowSize.Medium
        else -> WindowSize.Expanded
    }
}


//displayLarge (h1) para el elemento más destacado, la temperatura.
//displayMedium (span) para la unidad de temperatura (°C), como texto secundario alineado con el h1.
//headlineLarge (h2) para la descripción del clima.
//headlineMedium (h3) para el nombre de la ciudad.
//titleLarge (h4) para los días de la semana.
//bodyLarge (span) para las temperaturas diarias pequeñas, sin jerarquía de encabezado, ya que son datos secundarios.