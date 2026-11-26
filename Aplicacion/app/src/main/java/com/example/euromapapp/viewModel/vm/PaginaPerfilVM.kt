package com.example.euromapapp.viewModel.vm

import android.app.Application
import android.content.Context
import android.util.Base64
import androidx.lifecycle.AndroidViewModel
import com.example.euromapapp.viewModel.Uis.PaginaJuegoUiState
import com.example.euromapapp.viewModel.Uis.PaginaPerfilUiState
import com.example.euromapapp.viewModel.interfaces.Decompilador64
import com.example.euromapapp.viewModel.interfaces.RecursosString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PaginaPerfilVM(application: Application) : AndroidViewModel(application), Decompilador64, RecursosString {
    private val _uiState = MutableStateFlow(PaginaPerfilUiState())
    val uiState: StateFlow<PaginaPerfilUiState> = _uiState.asStateFlow()
    override val context: Context
        get() = getApplication<Application>().applicationContext


    override fun decodificarImagen(base64: String): ByteArray {
        val contenido = base64.substringAfter(",", base64).trim()
        return Base64.decode(contenido, Base64.DEFAULT)
    }

    fun drawableParaRango(char: Char): Int {
        val app = getApplication<Application>()
        return app.resources.getIdentifier(
            "rango_${char.lowercaseChar()}",
            "drawable",
            app.packageName
        )
    }
}
