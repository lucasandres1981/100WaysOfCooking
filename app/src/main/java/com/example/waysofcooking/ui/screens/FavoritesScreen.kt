package com.example.waysofcooking.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.waysofcooking.R

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
            imageResource = R.drawable.arroz_con_pollo,
            time = "15 min",
            difficulty = "Fácil",
            description = "Deliciosos panqueques con banana y miel..."
        ),
        Recipe(
            id = 2,
            name = "Otra Receta",
            imageResource = R.drawable.arroz_con_pollo,
            time = "20 min",
            difficulty = "Media",
            description = "Otra descripción..."
        )
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "100 Ways Of Cooking",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

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

@Composable
fun RecipeCard(recipe: Recipe, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = recipe.imageResource),
                    contentDescription = recipe.name,
                    modifier = Modifier.size(80.dp))

                            Spacer(modifier = Modifier.width(16.dp))

                            Column {
                        Text(
                            text = recipe.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "${recipe.time} - ${recipe.difficulty}",
                            fontSize = 14.sp
                        )
                    }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                  //  navController.navigate("recipeDetail/${recipe.id}")
                    navController.navigate("recipeSearch")
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Ver Receta")
            }
        }
    }
}