package com.example.euromapapp.viewModel.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.euromapapp.viewModel.Uis.DatosPaginaLoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PaginaInicioSesionVM(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(DatosPaginaLoginUiState())
    val uiState: StateFlow<DatosPaginaLoginUiState> = _uiState.asStateFlow()
    private val context: Context?
        get() = getApplication<Application>().applicationContext



    /**
     * cambia el nombre
     */
    fun setNombre(correo: String): String {
        _uiState.value = _uiState.value.copy(nombreOCorreo = correo)
        return _uiState.value.nombreOCorreo
    }

    /**
     * cambia la contraseña
     */
    fun setContrasena(contrasena: String): String {
        _uiState.value = _uiState.value.copy(contrasena = contrasena)
        return _uiState.value.contrasena
    }

    /**
     * permite que la contraseña esté oculta o no
     */
    fun setVisibilidad() {
        _uiState.value = _uiState.value.copy(contrasenaVisible = !uiState.value.contrasenaVisible)
    }

}