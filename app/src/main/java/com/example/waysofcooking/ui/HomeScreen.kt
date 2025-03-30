package com.example.waysofcooking.ui

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.waysofcooking.R

// ✅ Asegúrate de tener estas dos líneas ANTES de HomeScreen()
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
fun HomeScreen() {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "100 Ways Of Cooking",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("The 100 Easiest Recipes") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Buscar") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Checkbox(checked = true, onCheckedChange = {})
                Text("5m - 10m")
            }

            Row {
                Checkbox(checked = false, onCheckedChange = {})
                Text("Sorted Recipes")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ✅ Aquí usamos la lista 'recetas' correctamente
        LazyColumn {
            items(recetas.filter {
                it.nombre.contains(searchText, ignoreCase = true)
            }) { receta ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { /* Acción futura */ }
                ) {
                    Image(
                        painter = painterResource(id = receta.imagenResId),
                        contentDescription = receta.nombre,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(end = 16.dp)
                    )

                    Text(
                        text = receta.nombre,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.alignByBaseline()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
