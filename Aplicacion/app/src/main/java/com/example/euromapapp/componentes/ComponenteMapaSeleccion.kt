package com.example.euromapapp.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class DatosComponenteImagenMapaSeleccion(
   val id: Int,
   val nombre: String,
   val posicion: Int,
   val total: Int,
   val onAnterior: () -> Unit,
   val onSiguiente: () -> Unit,
   val modifier: Modifier = Modifier
)

/** Tarjeta de selección con imagen local, controles superpuestos, título e indicadores. */
@Composable
fun ComponenteImagenMapaSeleccion(
    datos: DatosComponenteImagenMapaSeleccion
) {
    val fondoTarjeta = Color(0xFF1C1F24)
    val azulIndicador = Color(0xFFA9C8FF)

    Column(
        modifier = datos.modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(fondoTarjeta)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(244.dp)
        ) {
            ComponenteImagenLocal(
                id = datos.id,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                forma = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )

            IconButton(
                onClick = datos.onAnterior,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp)
                    .size(48.dp)
                    .background(Color(0xB3101317), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "Mapa anterior",
                    tint = Color.LightGray
                )
            }

            IconButton(
                onClick = datos.onSiguiente,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp)
                    .size(48.dp)
                    .background(Color(0xB3101317), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Mapa siguiente",
                    tint = Color.LightGray
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = datos.nombre,
                color = Color(0xFFE5E5EA),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.padding(top = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(datos.total.coerceAtLeast(0)) { indice ->
                    Box(
                        modifier = Modifier
                            .size(
                                width = if (indice == datos.posicion) 22.dp else 8.dp,
                                height = 8.dp
                            )
                            .clip(CircleShape)
                            .background(
                                if (indice == datos.posicion) azulIndicador
                                else Color(0xFF454950)
                            )
                    )
                }
            }
        }
    }
}