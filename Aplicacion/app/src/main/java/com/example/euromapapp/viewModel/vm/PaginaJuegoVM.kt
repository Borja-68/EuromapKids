package com.example.euromapapp.viewModel.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.euromapapp.viewModel.Uis.PaginaJuegoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class PaginaJuegoVM(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(PaginaJuegoUiState())
    val uiState: StateFlow<PaginaJuegoUiState> = _uiState.asStateFlow()
    private val context: Context?
        get() = getApplication<Application>().applicationContext
    // val usuarioActual = PreferencesRepo(context!!)
}