package com.example.waysofcooking.ui.screens

import androidx.compose.runtime.Composable
import com.example.waysofcooking.ui.components.DrawerMenuContent
import com.example.waysofcooking.ui.components.MainScaffold
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun RegisterScreen(navController: NavHostController) {
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
                    text = "Apagina de registro",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Aquí va el contenido específico de esta pantalla
            }
        }
    )
}
