package com.example.app_del_clima_TP.presentacion.ciudad

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app_del_clima_TP.presentacion.events.EmptyView
import com.example.app_del_clima_TP.presentacion.events.ErrorView
import com.example.app_del_clima_TP.presentacion.events.LoadingSpinner
import com.example.app_del_clima_TP.repository.models.Ciudad

@Composable
fun CityView(
    state: CiudadState,
    execute: (CiudadIntention) -> Unit
) {
    var input by remember { mutableStateOf("") }

    TextField(
        value = input,
        label = { Text("Escriba el nombre de la ciudad") },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(590.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, MaterialTheme.colorScheme.onSurface, RoundedCornerShape(590.dp)),
        onValueChange = {
            input = it
            execute(CiudadIntention.Search(it))
        }
    )

    when(state) {
        is CiudadState.Loading -> LoadingSpinner()
        is CiudadState.Error -> ErrorView(state.message)
        is CiudadState.Success -> LoadCityList(state.cities) {
            execute(CiudadIntention.Select(it))
        }
        is CiudadState.Empty -> EmptyView()
    }
}

@Composable
private fun LoadCityList(cities: List<Ciudad>, onSelect: (Ciudad) -> Unit) {
    val modifier = Modifier
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        cities.forEach { city ->
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(60.dp)
                    .shadow(10.dp, RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp)),
                onClick = { onSelect(city) }
            ) {
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = city.name,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
