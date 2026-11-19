package com.example.euromapapp.vista.paginas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.euromapapp.componentes.ComponenteMapaInteractivo
import com.example.euromapapp.viewModel.vm.PaginaJuegoVM
var correctas =0
var incorrectas=0
var tiempoFinalMs : Long=0
@Composable
fun PaginaJuego(
//    datosJuegoVM: PaginaJuegoVM = viewModel(),
    accionFin: (String) -> Unit,
) {
//    val uiState by datosJuegoVM.uiState.collectAsState()
    ComponenteMapaInteractivo(
        idioma = "es",
        mapaNombre = "mp_espana",
        modifier = Modifier.fillMaxSize(),
        onCorrectas = { correctas = it },
        onIncorrectas = { incorrectas = it },
        onTiempoFinalizado = { tiempoFinalMs = it }
    )
    if(tiempoFinalMs!= 0.toLong()) accionFin

}

@Preview(showSystemUi = true)
@Composable
fun PaginaJuegoPreview() {
    PaginaJuego(accionFin = {  })
}