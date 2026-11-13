package com.example.euromapapp.componentes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class DatosTituloCaja(
   val titulo: String="",
   val tamanio: Int=20,
   
   val accion: () -> Unit = { Log.e("Testeo","tarjeta cliqueada")}
)
/**
 * crea un texto con un color de fondo predefinido
 * @param titulo
 * @param tamanio tamaño del texto
 */

@Composable
fun ComponenteTitulo(
    titulo: String,
    tamanio: Int=20
){
    Text(text = titulo,
        modifier = Modifier.background(MaterialTheme.colorScheme.primary,
            shape = CircleShape
        )
            .padding(vertical = 6.dp, horizontal = 20.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontSize = tamanio.sp,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = FontWeight.Bold,
        )
}

/**
 * crea un texto dentro de una caja con color
 * @param titulo
 * @param tamanio tamaño del texto
 */
@Composable
fun ComponenteTituloCaja(
    componenteTitulo: DatosTituloCaja
    ){
    Text(text = componenteTitulo.titulo,
        modifier = Modifier.background(MaterialTheme.colorScheme.primary)
            .padding(vertical = 6.dp, horizontal = 20.dp)
            .fillMaxWidth().clickable(onClick = {componenteTitulo.accion()}),
        textAlign = TextAlign.Center,
        fontSize = componenteTitulo.tamanio.sp,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = FontWeight.Bold,
        )
}



@Preview
@Composable
fun prevComponenteTitulo(){
    val titulo = "Título de prueba"
    val datotitulo = DatosTituloCaja(titulo = "Título de prueba")
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ComponenteTituloCaja(datotitulo)
        ComponenteTitulo(titulo)
    }
}
