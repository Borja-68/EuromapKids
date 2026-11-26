package com.example.euromapapp.vista.paginas

import TarjetaResultadoPerfil
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.euromapapp.R
import com.example.euromapapp.componentes.AceptarBoton
import com.example.euromapapp.componentes.ComponenteImagenRedondeadaLocal
import com.example.euromapapp.componentes.ComponenteListaTarjetasVerticalPerfil
import com.example.euromapapp.componentes.ComponenteTitulo
import com.example.euromapapp.componentes.DenegarBoton
import com.example.euromapapp.viewModel.vm.PaginaPerfilVM
import com.example.euromapapp.viewModel.vm.PaginaPrincipalVM

val tamanioImagenPerfil=150
val tamanioTarjetaPerfil=20

@Composable
fun PaginaPerfil(
    paginaPerfilUi : PaginaPerfilVM = viewModel(),
    accionCambioDatos: () -> Unit={}
){
    val uiState by paginaPerfilUi.uiState.collectAsState()
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

            ComponenteImagenRedondeadaLocal(
                id = uiState.imagenPerfil,
                tamanio = tamanioImagenPerfil
            )
            ComponenteTitulo(uiState.nombreUsuario)
            ComponenteTitulo(uiState.correoUsuario)
            AceptarBoton(
                msj = paginaPerfilUi.obtenerString("mensaje_boton_edicion","es"),
                accion = accionCambioDatos
            )
            ComponenteListaTarjetasVerticalPerfil(
                tipos = uiState.tarjetasUsuario.map {
                    TarjetaResultadoPerfil(
                    imagen = paginaPerfilUi.drawableParaRango(it.rango),
                    tipo = it.tipo,
                    tiempo = it.tiempo,
                    aciertos = it.aciertos,
                    nombreMapa = it.nombreMapa
                    )
                },
                tamanio = tamanioTarjetaPerfil
            )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun PrevPaginaPerfil() {
    PaginaPerfil()
}
