package com.example.euromapapp.viewModel.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.euromapapp.viewModel.Uis.DatosPaginaCambioDatosUiState
import com.example.euromapapp.viewModel.interfaces.RecursosString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PaginaCambioDatosVM (application: Application) : AndroidViewModel(application),
    RecursosString {
    private val _uiState = MutableStateFlow(DatosPaginaCambioDatosUiState())
    val uiState: StateFlow<DatosPaginaCambioDatosUiState> = _uiState.asStateFlow()
    override val context: Context
        get() = getApplication<Application>().applicationContext


    /**
     * cambia el nombre
     */
    fun setNombre(correo: String): String {
        _uiState.value = _uiState.value.copy(nombre = correo)
        return _uiState.value.nombre
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

    fun imagenAnterior(imagen: Int): Int{
        var ImagenNueva=imagen
        if(ImagenNueva==0){ImagenNueva = _uiState.value.imagenPerfil.size-1}
        else {ImagenNueva-=1}
        return ImagenNueva
    }

    fun imagenSiguiente(imagen: Int): Int{
        var ImagenNueva=imagen
        if(ImagenNueva==_uiState.value.imagenPerfil.size-1){ImagenNueva = 0}
        else {ImagenNueva+=1}
        return ImagenNueva
    }

    fun idiomaAnterior(idioma: Int): Int{
        var idiomaNuevo=idioma
        if(idiomaNuevo==0){idiomaNuevo = _uiState.value.idiomas.size-1}
        else {idiomaNuevo-=1}
        return idiomaNuevo
    }

    fun idiomaSiguiente(idioma: Int): Int{
        var idiomaNuevo=idioma
        if(idiomaNuevo==_uiState.value.idiomas.size-1){idiomaNuevo = 0}
        else {idiomaNuevo+=1}
        return idiomaNuevo
    }

    fun obtenerPosicionIdioma(idioma: String): Int{
        return _uiState.value.idiomas.indexOfFirst { it==idioma }
    }
}
