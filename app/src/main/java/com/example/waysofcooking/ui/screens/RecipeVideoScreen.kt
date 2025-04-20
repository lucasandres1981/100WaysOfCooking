package com.example.waysofcooking.ui.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController

@Composable
fun RecipeVideoScreen(videoId: String, navController: NavHostController) {
    val videoUrl = "https://www.youtube.com/embed/$videoId"

    AndroidView(
        factory = {
            WebView(it).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl(videoUrl)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
