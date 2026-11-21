package com.example.euromapapp.vista.paginas

import ComponenteTarjetaPuntuacion
import TarjetaResultado
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
import com.example.euromapapp.componentes.BotonesDobleAvanzarNivel
import com.example.euromapapp.componentes.ComponenteImagenLocal
import com.example.euromapapp.componentes.ComponenteTitulo
import com.example.euromapapp.componentes.ComponenteTituloCaja
import com.example.euromapapp.componentes.DatosBotonDoblePasaNivel
import com.example.euromapapp.componentes.DatosTituloCaja
import com.example.euromapapp.componentes.DenegarBoton
import com.example.euromapapp.viewModel.vm.PaginaSeleccionNivelVM
import com.example.euromapapp.viewModel.vm.PaginaSignUpVM

val tamanioTextoNombreMapaSeleccion=40
val tamanioImagenMapaSeleccion=400
val tamanioPuntuacionMapaSeleccion=35
val tamanioMirarClasificacionSeleccion=20


@Composable
fun PaginaSeleccionNivel(
    paginaSeleccionNivelUi : PaginaSeleccionNivelVM = viewModel(),
    accionSalida: () -> Unit={},
    accionClickImagen: () -> Unit={},
    onClickClasificacion: ()-> Unit={}
) {
    val uiState by paginaSeleccionNivelUi.uiState.collectAsState()

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
            DenegarBoton(
                msj = "Salir",
                modifier = Modifier.fillMaxWidth(),
                accion = accionSalida
            )
            ComponenteTitulo(
                titulo = uiState.nombreMapa,
                tamanio = tamanioTextoNombreMapaSeleccion
            )
            ComponenteImagenLocal(
                id = uiState.imagenMapa,
                tamanio = tamanioImagenMapaSeleccion
            )


            ComponenteTarjetaPuntuacion(
                contenido = uiState.puntuacion,
                tamanio = tamanioPuntuacionMapaSeleccion)

            ComponenteTituloCaja(
                componenteTitulo = DatosTituloCaja(titulo = "Mirar Clasificacion",
                    tamanio = tamanioMirarClasificacionSeleccion,
                    accion = {}
                )
            )

            BotonesDobleAvanzarNivel(
                datosBotones = DatosBotonDoblePasaNivel(
                    msjBot1 = "<-",
                    msjBot2 = "->",
                    tamanioTexto = tamanioMirarClasificacionSeleccion,
                    accionBoton1 = {
//                        if(posicionImagen==0)posicionImagen=imagenes.size -1
//                        else posicionImagen -=1
                    },
                    accionBoton2 ={
//                        if(posicionImagen==imagenes.size -1)posicionImagen=0
//                        else posicionImagen +=1
                    }
                )
            )
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun PrevPaginaSeleccionNivel() {
    PaginaSeleccionNivel(accionSalida = {}, accionClickImagen = {})
}
