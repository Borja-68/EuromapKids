package com.example.euromapapp.componentes


import ComponenteTarjetaHorizontal
import ComponenteTarjetaVertical
import Tarjeta
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * crea una lista de trajetas que se puede escrolear de manera vertical
 * @param tarjetas tarjetas que aparecerán en la lista
 * @param tamanioCaja la altura que tendrá donde se contiene la lista
 * @param tamanio tamaño de las tarjetas de dentro de la lista
 */
@Composable
fun ComponenteListaTarjetasVertical(
    tarjetas: List<Tarjeta>,
    tamanioCaja: Int = 250,
    tamanio: Int = 20
) {

    Box(
        Modifier.background(
            MaterialTheme.colorScheme.primary,
            RoundedCornerShape(8.dp)
        )
    ) {

        LazyColumn(
            modifier = Modifier
                .padding(all = (tamanio/2.5 ).dp)
                .fillMaxWidth()
                .height(height = tamanioCaja.dp),
            verticalArrangement = Arrangement.spacedBy((tamanio * 0.3).dp)
        )
        {
            items(tarjetas) { tarjeta ->
                ComponenteTarjetaHorizontal(contenido = tarjeta, tamanio = tamanio)
            }
        }
    }
}

/**
 * crea una lista de trajetas que se puede escrolear de manera horizontal
 * @param tarjetas tarjetas que aparecerán en la lista
 * @param tamanioCaja la altura que tendrá donde se contiene la lista
 * @param tamanio tamaño de las tarjetas de dentro de la lista
 */
@Composable
fun ComponenteListaTarjetasHorizontal(
    tarjetas: List<Tarjeta>,
    tamanioCaja: Int = 100,
    tamanio: Int = 20
) {

    Box(
        Modifier.background(
            MaterialTheme.colorScheme.secondary,
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = 8.dp,
                bottomEnd = 8.dp
            )
        )
    ) {

        LazyRow(
            modifier = Modifier
                .padding(all = (tamanio / 2).dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy((tamanio * 0.5).dp),
            verticalAlignment = Alignment.CenterVertically
            )
        {
            items(tarjetas) { tarjeta ->
                    ComponenteTarjetaVertical(contenido = tarjeta, tamanio = tamanio)
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun prevComponenteListaTarjetas() {
    val tarjetas: List<Tarjeta> = listOf(

    )
    Column {
    ComponenteListaTarjetasVertical(tarjetas);
    ComponenteListaTarjetasHorizontal(tarjetas)
    }
}