package com.example.waysofcooking.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.waysofcooking.R
import com.example.waysofcooking.data.RecetasDataSource
import com.example.waysofcooking.ui.components.MainScaffold
import com.example.waysofcooking.ui.components.DrawerMenuContent

@Composable
fun HomeScreen(navController: NavHostController) {
    val recetasPopulares = RecetasDataSource.recetas.shuffled().take(6)

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
                    onClick = { navController.navigate("search") },
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

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(recetasPopulares) { receta ->
                        Image(
                            painter = painterResource(id = receta.imagenResId),
                            contentDescription = receta.nombre,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clickable {
                                    val nombreId = receta.nombreId
                                    navController.navigate("recipe_detail/$nombreId")
                                },
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    )
}
