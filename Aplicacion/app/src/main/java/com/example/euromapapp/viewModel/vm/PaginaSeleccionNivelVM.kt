package com.example.euromapapp.viewModel.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.euromapapp.viewModel.Uis.DatosPaginaPrincipalUiState
import com.example.euromapapp.viewModel.Uis.PaginaSeleccionNivelUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PaginaSeleccionNivelVM(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(PaginaSeleccionNivelUiState())
    val uiState: StateFlow<PaginaSeleccionNivelUiState> = _uiState.asStateFlow()
    private val context: Context?
        get() = getApplication<Application>().applicationContext

}