package com.example.waysofcooking.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.waysofcooking.data.RecetasDataSource
import com.example.waysofcooking.ui.components.DrawerMenuContent
import com.example.waysofcooking.ui.components.MainScaffold

@Composable
fun RecipeDetailScreen(
    navController: NavHostController,
    nombreId: String?
) {
    Log.d("DETAIL_SCREEN", "Recibiendo nombreId: $nombreId")

    val receta = remember {
        mutableStateOf(
            RecetasDataSource.recetas.find {
                it.nombreId.lowercase().replace(" ", "-") == nombreId?.lowercase()?.replace(" ", "-")
            }
        )
    }

    MainScaffold(
        navController = navController,
        drawerContent = { scope, drawerState ->
            DrawerMenuContent(scope = scope, drawerState = drawerState, navController = navController)
        },
        content = { innerPadding ->

            receta.value?.let {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Botón de volver
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.align(Alignment.Start)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }

                    Text(text = it.nombre, style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(8.dp))

                    Image(
                        painter = painterResource(id = it.imagenResId),
                        contentDescription = it.nombre,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botón de favoritos
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconToggleButton(
                            checked = it.esFavorita,
                            onCheckedChange = { checked ->
                                receta.value = it.copy(esFavorita = checked)
                            }
                        ) {
                            Icon(
                                imageVector = if (it.esFavorita) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Favorito",
                                tint = if (it.esFavorita) Color.Red else Color.Gray
                            )
                        }
                        Text(
                            text = if (it.esFavorita) "Quitar de favoritos" else "Añadir a favoritos",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Ingredientes:", style = MaterialTheme.typography.titleMedium)
                    it.ingredientes.forEach { ingrediente ->
                        Text("- $ingrediente")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Pasos:", style = MaterialTheme.typography.titleMedium)
                    it.pasos.forEachIndexed { index, paso ->
                        Text("${index + 1}. $paso")
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { navController.navigate("videos") },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Ver Video")
                    }
                }
            } ?: run {
                Text(
                    text = "Receta no encontrada.",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    )
}
