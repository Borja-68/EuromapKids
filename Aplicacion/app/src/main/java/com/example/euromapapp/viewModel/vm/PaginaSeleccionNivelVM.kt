package com.example.euromapapp.viewModel.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.euromapapp.viewModel.Uis.DatosPaginaPrincipalUiState
import com.example.euromapapp.viewModel.Uis.PaginaSeleccionNivelUiState
import com.example.euromapapp.viewModel.interfaces.RecursosString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PaginaSeleccionNivelVM(application: Application) : AndroidViewModel(application), RecursosString {
    private val _uiState = MutableStateFlow(PaginaSeleccionNivelUiState())
    val uiState: StateFlow<PaginaSeleccionNivelUiState> = _uiState.asStateFlow()
    override val context: Context
        get() = getApplication<Application>().applicationContext

    fun mapaAnterior(mapa: Int): Int{
        var mapaNuevo=mapa
        if(mapaNuevo==0){mapaNuevo = _uiState.value.mapas.size-1}
        else {mapaNuevo-=1}
        return mapaNuevo
    }

    fun mapaSiguiente(mapa: Int): Int{
        var mapaNuevo=mapa
        if(mapaNuevo==_uiState.value.mapas.size-1){mapaNuevo = 0}
        else {mapaNuevo+=1}
        return mapaNuevo
    }

    fun obtenerPosicionMapaId(mapaId: Int): Int{
        return _uiState.value.mapas.indexOfFirst { it.idMapa==mapaId }
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
