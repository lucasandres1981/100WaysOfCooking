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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.waysofcooking.R
import androidx.navigation.NavHostController
import com.example.waysofcooking.ui.components.MainScaffold
import androidx.compose.ui.layout.ContentScale
import com.example.waysofcooking.ui.components.DrawerItem
import androidx.compose.material.icons.filled.Person
import com.example.waysofcooking.ui.components.DrawerMenuContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.unit.dp




@Composable
fun HomeScreen(navController: NavHostController) {
    MainScaffold(
        navController = navController,
        drawerContent = { scope, drawerState ->
            DrawerMenuContent(scope = scope, drawerState = drawerState, navController = navController)
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo de 100 Ways of Cooking",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                        .height(200.dp),


                    contentScale = ContentScale.Fit
                )

                Button(
                    onClick = { navController.navigate("Search") },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally)

                ) {
                    Text("Explorar Recetas")
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "🍽 Recetas Populares",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, bottom = 16.dp),
                    textAlign = TextAlign.Center
                )

                val recipeImages = listOf(
                    R.drawable.arroz_con_pollo,
                    R.drawable.ensalada_cesar_con_pollo,
                    R.drawable.grilled_cheese,
                    R.drawable.hamburguesa_clasica_con_queso,
                    R.drawable.spaghetti_carbonara,
                    R.drawable.pan_de_ajo,
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        //.height(240.dp)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(recipeImages) { imageRes ->
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = "Plato popular",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f) // cuadrado
                                .clickable {
                                    // Acción de navegación
                                },
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    )
}