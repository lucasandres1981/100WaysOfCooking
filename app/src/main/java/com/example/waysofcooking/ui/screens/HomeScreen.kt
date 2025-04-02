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




@Composable
fun HomeScreen(navController: NavHostController) {
    MainScaffold(
        navController = navController,
        drawerContent = { scope, drawerState ->
            Surface(color = Color.White) { //Fondo Blanco
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp)
                ) {
                    // Perfil del usuario
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .size(64.dp)
                                .padding(end = 12.dp)
                        )
                        Column {
                            Text("Lucas Murillo", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text(
                                "👾 Profile",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    Text(
                        "Products",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    DrawerItem("🍽 Recipies") { navController.navigate("recipeSearch") }
                    DrawerItem("🎥 Videos") { /* navController.navigate("videos") */ }
                    DrawerItem("❤️ Favorites") { /* navController.navigate("favorites") */ }
                    DrawerItem("🛍 Stores") { /* navController.navigate("stores") */ }

                    Spacer(modifier = Modifier.weight(1f))

                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    DrawerItem("⚙ Settings") { /* navController.navigate("settings") */ }
                    DrawerItem("📕 Log Out") { /* Acción de cierre de sesión */ }
                }
            }
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
                    onClick = { navController.navigate("recipeSearch") },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text("Explorar Recetas")
                }

                // Aquí colocas el contenido real de HomeScreen
                //Text(text = "Pantalla de inicio con menú hamburguesa")
            }
        }
    )
}
