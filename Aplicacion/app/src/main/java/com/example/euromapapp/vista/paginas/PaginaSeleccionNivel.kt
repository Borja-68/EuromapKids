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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.euromapapp.R
import com.example.euromapapp.componentes.BotonesDobleAvanzarNivel
import com.example.euromapapp.componentes.ComponenteImagenLocal
import com.example.euromapapp.componentes.ComponenteTitulo
import com.example.euromapapp.componentes.DatosBotonDoblePasaNivel
import com.example.euromapapp.componentes.DenegarBoton

@Composable
fun PaginaSeleccionNivel(
    accionSalida: () -> Unit,
    accionClickImagen: () -> Unit) {
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
            ComponenteTitulo(titulo = "Nombre mapa", tamanio = 40)
            ComponenteImagenLocal(id = R.drawable.picaxu, tamanio = 400)


            ComponenteTarjetaPuntuacion(
                contenido = TarjetaResultado(
                    imagen = R.drawable.picaxu,
                    aciertos = "10/10",
                    tiempo = "2:00:00"),
                tamanio = 35)


            BotonesDobleAvanzarNivel(
                datosBotones = DatosBotonDoblePasaNivel(
                    msjBot1 = "<-",
                    msjBot2 = "->",
                    tamanioTexto = 20,
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
