package com.example.euromapapp.componentes

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun  ComponeneteMapaInteractivo(
    url:String,
    modifier: Modifier = Modifier
){
    AndroidView(modifier = modifier,
        factory = {context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        },
        update = {miWebView  ->
        if (miWebView .url!=url) miWebView.loadUrl(url)
        }
    )
}