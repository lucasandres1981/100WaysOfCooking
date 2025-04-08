package com.example.waysofcooking.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import com.example.waysofcooking.R

import androidx.navigation.NavHostController
import androidx.compose.material3.DrawerState
import androidx.compose.material.icons.filled.Person
import com.example.waysofcooking.ui.components.DrawerItem
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip



@Composable
fun DrawerMenuContent(
    navController: NavHostController,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    Surface(
        modifier = Modifier.fillMaxHeight(),
        color = Color.White //  Fondo Menu
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // 👤 Perfil del usuario
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lucas),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .padding(end = 12.dp)
                )
                Column {
                    Text("Lucas Murillo", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    //Text(
                    //    "👾 Profile",
                    //    fontSize = 14.sp,
                    //    color = MaterialTheme.colorScheme.onSurfaceVariant
                    //)
                    DrawerItem("👾 Profile") {
                        scope.launch {
                            drawerState.close()
                            delay(250)
                            navController.navigate("profile")
                        }
                    }

                }

            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))



            Text(
                text = "Products",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom =16.dp)
            )

            // Menú de navegación con animación
            DrawerItem("🍽 Recipies") {
                scope.launch {
                    drawerState.close()
                    delay(250)
                    navController.navigate("search")
                }
            }

            DrawerItem("🎥 Videos") {
                scope.launch {
                    drawerState.close()
                    delay(250)
                    navController.navigate("videos")
                }
            }

            DrawerItem("❤️ Favorites") {
                scope.launch {
                    drawerState.close()
                    delay(250)
                    navController.navigate("favorites")
                }
            }

            DrawerItem("🛍 Stores") {
                scope.launch {
                    drawerState.close()
                    delay(250)
                    navController.navigate("store")
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Divider(modifier = Modifier.padding(vertical = 12.dp))

            //DrawerItem("⚙ Settings") {
            //    scope.launch {
           //         drawerState.close()
           //         delay(250)
            //        navController.navigate("settings")
           //     }
           // }

            DrawerItem("📕 Log Out") {
                scope.launch {
                    drawerState.close()
                    delay(250)
                    // Acción de cerrar sesión
                }
            }
        }
    }
}
