package com.example.waysofcooking.ui.screens

import androidx.compose.runtime.Composable
import com.example.waysofcooking.ui.components.DrawerMenuContent
import com.example.waysofcooking.ui.components.MainScaffold
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.waysofcooking.R
import androidx.navigation.NavHostController
import com.example.waysofcooking.ui.components.MainScaffold
import com.example.waysofcooking.ui.components.DrawerMenuContent


@Composable
fun SettingsScreen(navController: NavHostController) {
    MainScaffold(
        navController = navController,
        drawerContent = { scope, drawerState ->
            DrawerMenuContent(
                scope = scope,
                drawerState = drawerState,
                navController = navController
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp) // ajustar este padding segun lo requerido no mas de 26 para que no quede muy grande
            ) {
                Text(
                    text = "Pantalla de Settings",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Aquí va el contenido específico de esta pantalla
            }
        }
    )
}
