package com.example.euromapapp.viewModel.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.euromapapp.viewModel.Uis.PaginaJuegoUiState
import com.example.euromapapp.viewModel.Uis.PaginaPerfilUiState
import com.example.euromapapp.viewModel.interfaces.RecursosString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class PaginaJuegoVM(application: Application) : AndroidViewModel(application), RecursosString {
    private val _uiState = MutableStateFlow(PaginaJuegoUiState())
    val uiState: StateFlow<PaginaJuegoUiState> = _uiState.asStateFlow()
    override val context: Context
        get() = getApplication<Application>().applicationContext

    fun cargarIdiomaYMapa(idioma:String , idMapa: Int){
        _uiState.value = _uiState.value.copy(idioma = idioma)
        _uiState.value = _uiState.value.copy(idMapa = idMapa)
    }
}
