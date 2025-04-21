package com.example.waysofcooking.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.waysofcooking.data.RecetasDataSource
import com.example.waysofcooking.data.FavoriteRecipesStorage
import com.example.waysofcooking.data.SessionManager
import com.example.waysofcooking.ui.components.AppButton
import com.example.waysofcooking.ui.components.DrawerMenuContent
import com.example.waysofcooking.ui.components.MainScaffold

@Composable
fun FavoritesScreen(navController: NavHostController) {
    val context = LocalContext.current
    val nickName = remember { SessionManager.getLoggedUserNick(context) }

    var favoriteRecipes by remember {
        mutableStateOf(
            if (nickName != null) {
                val favs = FavoriteRecipesStorage.getFavorites(context, nickName)
                RecetasDataSource.recetas.filter { it.nombreId in favs }
            } else emptyList()
        )
    }

    fun removeFromFavorites(nombreId: String) {
        if (nickName != null) {
            FavoriteRecipesStorage.removeFavorite(context, nickName, nombreId)
            favoriteRecipes = favoriteRecipes.filter { it.nombreId != nombreId }
        }
    }

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
                Text(
                    text = "Recetas Favoritas",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                if (favoriteRecipes.isEmpty()) {
                    Text(
                        text = "No tienes recetas favoritas aún.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                } else {
                    LazyColumn {
                        items(favoriteRecipes) { receta ->
                            FavoriteRecipeCard(
                                nombre = receta.nombre,
                                imagenResId = receta.imagenResId,
                                nombreId = receta.nombreId,
                                navController = navController,
                                onRemove = { removeFromFavorites(receta.nombreId) }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun FavoriteRecipeCard(
    nombre: String,
    imagenResId: Int,
    nombreId: String,
    navController: NavHostController,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = imagenResId),
                    contentDescription = nombre,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 16.dp)
                )

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = nombre,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                IconButton(onClick = onRemove) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Quitar de favoritos",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            AppButton(
                text = "Ver Receta",
                onClick = {
                    val safeNombreId = nombreId.replace(" ", "-")
                    navController.navigate("recipe_detail/$safeNombreId")
                }
            )
        }
    }
}
