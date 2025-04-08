package com.example.waysofcooking.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity

class YouTubeWebViewActivity : ComponentActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val webView = WebView(this)
        setContentView(webView)

        val videoUrl = intent.getStringExtra("video_url")

        webView.webViewClient = WebViewClient()
        val settings: WebSettings = webView.settings
        settings.javaScriptEnabled = true

        videoUrl?.let {
            webView.loadUrl(it)
        } ?: run {
            webView.loadData("<h2>Video no disponible</h2>", "text/html", "UTF-8")
        }
    }
}
