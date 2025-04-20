package com.example.waysofcooking.ui.screens


import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class YouTubeWebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val webView = WebView(this)
        setContentView(webView)

        val videoUrl = intent.getStringExtra("video_url") ?: return
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(videoUrl)
    }
}
