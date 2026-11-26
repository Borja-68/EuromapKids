package com.example.euromapapp.viewModel.interfaces

import android.content.Context

interface RecursosString {
    val context: Context

    fun obtenerString(nombre: String, idioma: String): String {
        val id = context.resources.getIdentifier(
            "${nombre}_$idioma",
            "string",
            context.packageName
        )
        return if (id != 0) context.getString(id) else ""
    }
}
