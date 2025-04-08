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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.waysofcooking.data.RecetasDataSource
import android.util.Log
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.IconToggleButton
import androidx.compose.ui.graphics.Color



@Composable
fun RecipeDetailScreen(
    navController: NavHostController,
    nombreId: String?
) {
    Log.d("DETAIL_SCREEN", "Recibiendo nombreId: $nombreId")

    val receta = remember { mutableStateOf(RecetasDataSource.recetas.find { it.nombreId == nombreId }) }

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
                        onClick = { navController.navigate("home") },
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

                    // Botón de añadir a favoritos con label
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

                    // Botón de ver video
                    Button(
                        onClick = { navController.navigate("videos") },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Ver Video")
                    }
                }
            } ?: Text("Receta no encontrada.")
        }
    )
}
