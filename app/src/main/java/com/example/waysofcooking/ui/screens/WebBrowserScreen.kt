package com.example.waysofcooking.ui.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.waysofcooking.ui.components.DrawerMenuContent
import com.example.waysofcooking.ui.components.MainScaffold

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebBrowserScreen(navController: NavHostController) {
    val context = LocalContext.current
    var url by remember { mutableStateOf("https://") }
    var currentUrl by remember { mutableStateOf("https://www.google.com") }

    MainScaffold(
        navController = navController,
        drawerContent = { scope, drawerState ->
            DrawerMenuContent(navController, scope, drawerState)
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = url,
                    onValueChange = { url = it },
                    label = { Text("Escribe la URL") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { currentUrl = url },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ir")
                }

                Spacer(modifier = Modifier.height(16.dp))

                AndroidView(
                    factory = {
                        WebView(context).apply {
                            webViewClient = WebViewClient()
                            settings.javaScriptEnabled = true
                            loadUrl(currentUrl)
                        }
                    },
                    update = { it.loadUrl(currentUrl) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
        }
    )
}
