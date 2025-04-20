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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import com.example.waysofcooking.data.FavoriteRecipesStorage
import com.example.waysofcooking.data.Receta
import com.example.waysofcooking.data.RecetasDataSource
import com.example.waysofcooking.data.SessionManager
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite


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
    val context = LocalContext.current
    val nickName = remember { SessionManager.getLoggedUserNick(context) }
    val recetas = RecetasDataSource.recetas

    val favoritasIds = remember(nickName) {
        nickName?.let {
            FavoriteRecipesStorage.getFavorites(context, it)
        } ?: emptyList()
    }

    val favoriteRecipes = recetas.filter { it.nombreId in favoritasIds }

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
                    text = "Mis Recetas Favoritas",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (favoriteRecipes.isEmpty()) {
                    Text("No tienes recetas favoritas aún.")
                } else {
                    LazyColumn {
                        items(favoriteRecipes) { receta ->
                            RecipeCardFavorita(receta = receta, navController = navController)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun RecipeCardFavorita(receta: Receta, navController: NavHostController) {
    val context = LocalContext.current
    val nickName = remember { SessionManager.getLoggedUserNick(context) }
    var removed = remember { mutableStateOf(false) }

    if (removed.value) return    // Oculta la tarjeta si ya fue eliminada

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
                    painter = painterResource(id = receta.imagenResId),
                    contentDescription = receta.nombre,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 16.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = receta.nombre,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // ❤️ Botón para eliminar
                IconButton(
                    onClick = {
                        if (nickName != null) {
                            FavoriteRecipesStorage.removeFavorite(context, nickName, receta.nombreId)
                            removed.value = true

                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Quitar de favoritos",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    navController.navigate("recipe_detail/${receta.nombreId}")
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Ver Receta")
            }

        }
    }
}
