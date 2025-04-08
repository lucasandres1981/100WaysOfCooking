package com.example.waysofcooking.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.waysofcooking.R

class YouTubeWebViewActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_web_view)  // <-- Usamos el layout XML

        val webView = findViewById<WebView>(R.id.web_view)
        val backButton = findViewById<Button>(R.id.btn_back)

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        val videoUrl = intent.getStringExtra("video_url")
        Log.d("YouTubeWebViewActivity", "URL recibida: $videoUrl")

        if (videoUrl != null) {
            webView.loadUrl(videoUrl)
        } else {
            Toast.makeText(this, "No se recibió la URL del video", Toast.LENGTH_LONG).show()
            finish()
        }

        backButton.setOnClickListener {
            finish()  // Cierra la actividad al hacer clic
        }
    }
}
