package com.example.euromapapp.componentes

import android.webkit.WebView
import android.webkit.JavascriptInterface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun ComponenteMapaInteractivo(
    idioma: String,
    mapaNombre: String,
    modifier: Modifier = Modifier,
    onCorrectas: (Int) -> Unit = {},
    onIncorrectas: (Int) -> Unit = {},
    onTiempoFinalizado: (Long) -> Unit = {}
) {
    val pageKey = "$idioma|$mapaNombre"
    val latestOnCorrectas = rememberUpdatedState(onCorrectas)
    val latestOnIncorrectas = rememberUpdatedState(onIncorrectas)
    val latestOnTiempoFinalizado = rememberUpdatedState(onTiempoFinalizado)

    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.allowFileAccess = true
                addJavascriptInterface(object {
                    @JavascriptInterface
                    fun onCorrectas(total: Int) {
                        post { latestOnCorrectas.value(total) }
                    }

                    @JavascriptInterface
                    fun onIncorrectas(total: Int) {
                        post { latestOnIncorrectas.value(total) }
                    }

                    @JavascriptInterface
                    fun onTiempoFinalizado(tiempoMs: Long) {
                        post { latestOnTiempoFinalizado.value(tiempoMs) }
                    }
                }, "AndroidBridge")
                loadMapPage(this, context.assets, idioma, mapaNombre, pageKey)
            }
        },
        update = { webView ->
            if (webView.tag != pageKey) {
                loadMapPage(webView, webView.context.assets, idioma, mapaNombre, pageKey)
            }
        }
    )
}

private fun loadMapPage(
    webView: WebView,
    assets: android.content.res.AssetManager,
    idioma: String,
    mapaNombre: String,
    pageKey: String
) {
    val pagina = assets.open("componentes_juego/baseMapa.html")
        .bufferedReader().use { it.readText() }
    val svg = assets.open("datos_mapas/svgs/$mapaNombre.svg")
        .bufferedReader().use { it.readText() }
    val json = assets.open("datos_mapas/jsons/$mapaNombre.json")
        .bufferedReader().use { it.readText() }
    val html = pagina
        .replace("<!--MAP_SVG-->", svg)
        .replace("<!--MAP_JSON-->", json)
        .replace("<!--MAP_LANG-->", idioma)

    webView.tag = pageKey
    webView.loadDataWithBaseURL(
        "file:///android_asset/componentes_juego/",
        html,
        "text/html",
        "UTF-8",
        null
    )
}
