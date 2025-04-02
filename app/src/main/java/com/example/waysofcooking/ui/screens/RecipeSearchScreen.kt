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
import com.example.waysofcooking.R
import androidx.navigation.NavHostController
import com.example.waysofcooking.ui.components.MainScaffold
import com.example.waysofcooking.ui.components.DrawerMenuContent

data class Receta(val nombre: String, val imagenResId: Int)

val recetas = listOf(
    Receta("Arroz con Pollo", R.drawable.arroz_con_pollo),
    Receta("Ensalada César con Pollo", R.drawable.ensalada_cesar_con_pollo),
    Receta("Grilled Cheese", R.drawable.grilled_cheese),
    Receta("Hamburguesa Clásica con Queso", R.drawable.hamburguesa_clasica_con_queso),
    Receta("Pan de Ajo", R.drawable.pan_de_ajo),
    Receta("Papas Fritas", R.drawable.papas_fritas),
    Receta("Spaghetti Carbonara", R.drawable.spaghetti_carbonara),
    Receta("Tacos al Pastor", R.drawable.tacos_al_pastor)
)

@Composable
fun RecipeSearchScreen(navController: NavHostController) {
    MainScaffold(
        navController = navController,
        drawerContent = { scope, drawerState ->
            DrawerMenuContent(navController, scope, drawerState)
        },
        content = { innerPadding ->
            var searchText by remember { mutableStateOf("") }

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
                    items(recetas.filter { it.nombre.contains(searchText, ignoreCase = true) }) { receta ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { }
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
