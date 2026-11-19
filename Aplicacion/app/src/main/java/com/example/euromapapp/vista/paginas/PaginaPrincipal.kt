package com.example.euromapapp.vista.paginas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.euromapapp.componentes.ComponenteListaTarjetasVerticalTipos
import com.example.euromapapp.componentes.DatosTituloCaja

val tipos: List<DatosTituloCaja> = listOf(DatosTituloCaja(titulo = "Politicos"),
    DatosTituloCaja(titulo = "Politicos"),
    DatosTituloCaja(titulo = "Politicos"),
    DatosTituloCaja(titulo = "Politicos"),
    DatosTituloCaja(titulo = "Politicos"))

@Composable
fun PaginaPrincipal(
    accionClick: () -> Unit
){
    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {

        ComponenteListaTarjetasVerticalTipos(tipos = tipos, tamanioCaja = 500 ,tamanio = 40)
    }

}


@Preview(showSystemUi = true)
@Composable
fun PrevPaginaPrincipal() {
    PaginaPrincipal(accionClick = {})
}
