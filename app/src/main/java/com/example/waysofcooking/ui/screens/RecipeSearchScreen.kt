package com.example.waysofcooking.ui.screens

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
import androidx.navigation.NavHostController
import com.example.waysofcooking.ui.components.MainScaffold
import com.example.waysofcooking.ui.components.DrawerMenuContent
import com.example.waysofcooking.data.RecetasDataSource

@Composable
fun RecipeSearchScreen(navController: NavHostController) {
    MainScaffold(
        navController = navController,
        drawerContent = { scope, drawerState ->
            DrawerMenuContent(navController, scope, drawerState)
        },
        content = { innerPadding ->
            var searchText by remember { mutableStateOf("") }

            // Lista filtrada en tiempo real
            val recetasFiltradas = RecetasDataSource.recetas.filter {
                it.nombre.contains(searchText, ignoreCase = true)
            }

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("The 100 Easiest Recipes") },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Buscar") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(recetasFiltradas) { receta ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    // para navegar ala receta en RecipeDetailScreen
                                    // navController.navigate("recipeDetail/${receta.nombre}")
                                }
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = receta.imagenResId),
                                contentDescription = receta.nombre,
                                modifier = Modifier.size(80.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = receta.nombre,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }
    )
}
