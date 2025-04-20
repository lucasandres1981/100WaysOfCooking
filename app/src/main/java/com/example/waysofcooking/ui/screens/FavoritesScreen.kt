package com.example.waysofcooking.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.waysofcooking.R
import com.example.waysofcooking.ui.components.DrawerMenuContent
import com.example.waysofcooking.ui.components.MainScaffold
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip



data class Recipe(
    val id: Int,
    val name: String,
    val imageResource: Int,
    val time: String,
    val difficulty: String,
    val description: String
)

@Composable
fun FavoritesScreen(navController: NavHostController) {
    val favoriteRecipes = listOf(
        Recipe(
            id = 1,
            name = "Panqueques con Banana y Miel",
            imageResource = R.drawable.waffles_de_avena,
            time = "15 min",
            difficulty = "Fácil",
            description = "Deliciosos panqueques con banana y miel..."
        ),
        Recipe(
            id = 2,
            name = "Arroz con Pollo",
            imageResource = R.drawable.arroz_con_pollo,
            time = "20 min",
            difficulty = "Media",
            description = "Una receta tradicional perfecta para cualquier ocasión."
        )
    )

    MainScaffold(
        navController = navController,
        drawerContent = { scope, drawerState ->
            DrawerMenuContent(navController = navController, scope = scope, drawerState = drawerState)
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Favoritos",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold

                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(favoriteRecipes) { recipe ->
                        RecipeCard(recipe = recipe, navController = navController)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    )
}

@Composable
fun RecipeCard(recipe: Recipe, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = recipe.imageResource),
                    contentDescription = recipe.name,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 16.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Column {
                    Text(
                        text = recipe.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${recipe.time} - ${recipe.difficulty}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    navController.navigate("Search")
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Ver Receta")
            }
        }
    }
}
