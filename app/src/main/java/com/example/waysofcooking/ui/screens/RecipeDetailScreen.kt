package com.example.waysofcooking.ui.screens

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.waysofcooking.data.RecetasDataSource
import com.example.waysofcooking.ui.components.AppButton
import com.example.waysofcooking.ui.components.DrawerMenuContent
import com.example.waysofcooking.ui.components.MainScaffold
import com.example.waysofcooking.data.FavoriteRecipesStorage
import com.example.waysofcooking.data.SessionManager
import androidx.compose.ui.platform.LocalContext
import com.example.waysofcooking.data.RecetasVideoData


@Composable
fun RecipeDetailScreen(
    navController: NavHostController,
    nombreId: String?

)

{
    Log.d("DETAIL_SCREEN", "Recibiendo nombreId: $nombreId")

    val receta = remember {
        mutableStateOf(
            RecetasDataSource.recetas.find {
                it.nombreId.lowercase().replace(" ", "-") == nombreId?.lowercase()?.replace(" ", "-")
            }
        )
    }

    val context = LocalContext.current
    val nickName = remember { SessionManager.getLoggedUserNick(context) }

    var isFavorite by remember { mutableStateOf(false) }

    LaunchedEffect(receta.value) {
        receta.value?.let {
            if (nickName != null) {
                isFavorite = FavoriteRecipesStorage.isFavorite(context, nickName, it.nombreId)
            }
        }
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
                        .verticalScroll(rememberScrollState())
                )

                {

                    Text(
                        text = it.nombre,
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Image(
                        painter = painterResource(id = it.imagenResId),
                        contentDescription = it.nombre,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .shadow(elevation = 10.dp, shape = RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop

                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botón de favoritos
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconToggleButton(
                                checked = isFavorite,
                                onCheckedChange = { checked ->
                                    isFavorite = checked
                                    receta.value?.let {
                                        if (nickName != null) {
                                            if (checked) {
                                                FavoriteRecipesStorage.saveFavorite(context, nickName, it.nombreId)
                                            } else {
                                                FavoriteRecipesStorage.removeFavorite(context, nickName, it.nombreId)
                                            }
                                        }
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = null,
                                    tint = if (isFavorite) Color.Red else Color.Gray
                                )
                            }
                            Text(
                                text = if (isFavorite) "Quitar de favoritos" else "Añadir a favoritos",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }


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

                    RecetasVideoData.videos[nombreId]?.let { videoInfo ->
                        AppButton(
                            text = "Ver Video",
                            onClick = {
                                val intent = Intent(context, YouTubeWebViewActivity::class.java).apply {
                                    putExtra("video_url", "https://www.youtube.com/embed/${videoInfo.videoId}")
                                }
                                context.startActivity(intent)
                            }
                        )
                    }



                    Spacer(modifier = Modifier.height(12.dp))

                    AppButton(
                        text = "Buscar ingredientes en tiendas cercanas",
                        onClick = { navController.navigate("store") }
                    )

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
