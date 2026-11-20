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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.euromapapp.R
import com.example.euromapapp.componentes.ComponenteImagenLocal
import com.example.euromapapp.componentes.ComponenteImagenRedondeadaLocal
import com.example.euromapapp.componentes.ComponenteListaTarjetasVerticalPerfil
import com.example.euromapapp.componentes.ComponenteTitulo
import com.example.euromapapp.componentes.DenegarBoton

val resultados: List<TarjetaResultadoPerfil> = listOf(
    TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
    TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
    TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
    TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
    TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
    TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
    TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
    TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
    TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
    TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
    TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
    TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
    TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
    TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
    TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
    TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
    TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10")
    )

@Composable
fun PaginaPerfil(
    accionSalida: () -> Unit
){
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

            ComponenteImagenRedondeadaLocal(R.drawable.picaxu, tamanio = 150)
            ComponenteTitulo("Nombre")
            ComponenteTitulo("Correo")
            ComponenteListaTarjetasVerticalPerfil(tipos = resultados, tamanio = 20)
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun PrevPaginaPerfil() {
    PaginaPerfil(accionSalida = {})
}
