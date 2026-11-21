package com.example.euromapapp.vista.paginas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.euromapapp.componentes.ComponenteListaTarjetasVerticalClasificacion
import com.example.euromapapp.componentes.ComponenteTitulo
import com.example.euromapapp.componentes.DenegarBoton
import com.example.euromapapp.viewModel.vm.PaginaClasificacion

val tamanioCajaClasificacion=500
val tamanioTextoClasificacion=25
@Composable
fun PaginaClasificacion(
    paginaClasificacionUi : PaginaClasificacion = viewModel(),
    accionSalida: () -> Unit={}
)
{
    val uiState by paginaClasificacionUi.uiState.collectAsState()
    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 24.dp),
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            DenegarBoton(msj = "Salir",
                modifier = Modifier.fillMaxWidth(),
                accion = accionSalida
            )
            ComponenteTitulo(titulo = uiState.nombremapa)
            ComponenteListaTarjetasVerticalClasificacion(
                tipos = uiState.clasificacion,
                tamanioCaja = tamanioCajaClasificacion,
                tamanio = tamanioTextoClasificacion)


        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PaginaClasificacionPreview() {
    PaginaClasificacion(accionSalida = {  })
}