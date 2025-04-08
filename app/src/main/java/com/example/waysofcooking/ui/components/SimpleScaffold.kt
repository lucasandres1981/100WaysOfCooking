package com.example.waysofcooking.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleScaffold(
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("100 Ways Of Cooking") },
                actions = {
                    TextButton(onClick = {
                        navController.navigate("login")
                    }) {
                        Text("Login")
                    }
                }
            )
        },
        content = content
    )
}
