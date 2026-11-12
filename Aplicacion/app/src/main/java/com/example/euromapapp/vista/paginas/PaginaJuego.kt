package com.example.euromapapp.vista.paginas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.euromapapp.componentes.ComponenteMapaInteractivo
import com.example.euromapapp.viewModel.vm.PaginaJuegoVM

//@Composable
//fun PaginaJuego(
//    datosJuegoVM: PaginaJuegoVM = viewModel(),
//    accionFin: (String) -> Unit,
//) {
//    val uiState by datosJuegoVM.uiState.collectAsState()
//    ComponenteMapaInteractivo(mapaNombre = uiState.idMapa+"", idioma = )
//
//}