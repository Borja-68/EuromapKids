package com.example.euromapapp.viewModel.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.euromapapp.viewModel.Uis.PaginaClasificacionUiState
import com.example.euromapapp.viewModel.Uis.PaginaPerfilUiState
import com.example.euromapapp.viewModel.interfaces.RecursosString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PaginaClasificacion(application: Application) : AndroidViewModel(application), RecursosString {
    private val _uiState = MutableStateFlow(PaginaClasificacionUiState())
    val uiState: StateFlow<PaginaClasificacionUiState> = _uiState.asStateFlow()
    override val context: Context
        get() = getApplication<Application>().applicationContext

}
