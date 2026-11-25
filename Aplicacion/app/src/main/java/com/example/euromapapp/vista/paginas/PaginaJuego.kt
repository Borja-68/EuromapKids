package com.example.euromapapp.vista.paginas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.euromapapp.componentes.ComponenteMapaInteractivo
import com.example.euromapapp.viewModel.vm.PaginaJuegoVM
import com.example.euromapapp.viewModel.vm.PaginaPerfilVM


@Composable
fun PaginaJuego(
    idMapa: Int = 1,
    tipoMapa:String ="la",
    paginaJuegoUi : PaginaJuegoVM = viewModel(),
    accionFin: (String,Int) -> Unit={_,_->},
) {
    paginaJuegoUi.cargarIdiomaYMapa(
        idioma = "es",
        idMapa = idMapa
    )
    val uiState by paginaJuegoUi.uiState.collectAsState()
    ComponenteMapaInteractivo(
        idioma = uiState.idioma,
        mapaNombre = "mp_"+uiState.idMapa,
        modifier = Modifier.fillMaxSize(),
        onCorrectas = { uiState.correctas = it },
        onIncorrectas = { uiState.Incorrectas = it },
        onTiempoFinalizado = { tiempoMs ->
            if (tiempoMs > 0L) {
                uiState.tiempoFinalMs=tiempoMs
                accionFin(tipoMapa, idMapa)
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun PaginaJuegoPreview() {
    PaginaJuego()
}
