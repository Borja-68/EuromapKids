package com.example.euromapapp.viewModel.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.euromapapp.viewModel.Uis.MainUiState
import com.example.euromapapp.viewModel.interfaces.RecursosString

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(application: Application) : AndroidViewModel(application), RecursosString {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()
    override val context: Context
        get() = getApplication<Application>().applicationContext

    fun setModoOscuro(){
        _uiState.value = _uiState.value.copy(modoOscuro = !uiState.value.modoOscuro)
    }
    fun getModoOscuro(): Boolean{
       return _uiState.value.modoOscuro
    }
    fun setModoOscuroConcreto(modoPrevio: Boolean, accion: ()-> Unit){
        _uiState.value = _uiState.value.copy(modoOscuro = modoPrevio)
        accion()
    }

    fun setTopBarMainConcreto(oculto: Boolean){
        _uiState.value = _uiState.value.copy(topbarMain = oculto)
    }

    fun setTopbarAccionAtrasConcreto(oculto: Boolean){
        _uiState.value = _uiState.value.copy(topbarAccionAtras = oculto)
    }

    fun setAccionRegreso(accion: ()-> Unit){
        _uiState.value = _uiState.value.copy(accionRegreso = accion)
    }

    fun setMensaje(mensaje: String){
        _uiState.value = _uiState.value.copy(mensaje = mensaje)
    }

    fun getMensaje(): String{
        return _uiState.value.mensaje
    }
    fun getLogueado(): Boolean{
        return _uiState.value.logueado
    }

    fun getIdioma(): String{
        return _uiState.value.idioma
    }

    fun setIdioma(idioma: String){
        _uiState.value = _uiState.value.copy(idioma = idioma)
    }
}

