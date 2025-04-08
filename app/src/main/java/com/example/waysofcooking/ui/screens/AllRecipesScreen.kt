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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.waysofcooking.R
import androidx.navigation.NavHostController
import com.example.waysofcooking.ui.components.MainScaffold
import com.example.waysofcooking.ui.components.DrawerMenuContent
import com.example.waysofcooking.data.RecetasDataSource
import android.util.Log

@Composable
fun AllRecipesScreen(navController: NavHostController) {
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
                    .padding(16.dp)
            ) {
                Text(
                    text = "Página de Todas las recetas",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                val recetas = RecetasDataSource.recetas

                LazyColumn(modifier = Modifier.padding(8.dp)) {
                    items(recetas) { receta ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    Log.d("NAVIGATION", "Navegando a: recipeDetail/${receta.nombreId}")
                                    navController.navigate("recipeDetail/${receta.nombreId}")
                                }
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = receta.imagenResId),
                                contentDescription = receta.nombre,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = receta.nombre,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    )
}
