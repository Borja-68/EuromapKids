package com.example.euromapapp.vista.paginas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.euromapapp.componentes.ComponenteListaTarjetasVerticalTipos
import com.example.euromapapp.componentes.DatosTituloCaja
import com.example.euromapapp.viewModel.vm.PaginaPrincipalVM

val tipos: List<DatosTituloCaja> = listOf(DatosTituloCaja(titulo = "Politicos"),
    DatosTituloCaja(titulo = "Politicos"),
    DatosTituloCaja(titulo = "Politicos"),
    DatosTituloCaja(titulo = "Politicos"),
    DatosTituloCaja(titulo = "Politicos"))

val tamanioCajaPrincipal=500
val tamanioTextoPrincipal=40
@Composable
fun PaginaPrincipal(
    paginaPrincipalUi : PaginaPrincipalVM = viewModel(),
    onItemClick: (String,Int) -> Unit={_,_ ->}

){
    val uiState by paginaPrincipalUi.uiState.collectAsState()
    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {

        ComponenteListaTarjetasVerticalTipos(
            tipos = uiState.tipos.map {
                DatosTituloCaja(
                    titulo = it.titulo,
                    accion = {onItemClick(it.titulo,0)}
                )
            },
            tamanioCaja = tamanioCajaPrincipal,
            tamanio = tamanioTextoPrincipal,

            )
    }

}


@Preview(showSystemUi = true)
@Composable
fun PrevPaginaPrincipal() {
    PaginaPrincipal()
}
