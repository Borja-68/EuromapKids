package com.example.euromapapp.viewModel.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.euromapapp.viewModel.Uis.MainUiState

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()
    private val context: Context?
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
}

