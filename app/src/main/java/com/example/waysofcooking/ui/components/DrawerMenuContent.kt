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
import com.example.waysofcooking.data.SessionManager
import com.example.waysofcooking.data.UserTxtStorage
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.foundation.border
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import com.example.waysofcooking.data.AvatarStorage


@Composable
fun DrawerMenuContent(
    navController: NavHostController,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    val context = LocalContext.current
    val nickName = remember { SessionManager.getLoggedUserNick(context) }
    val user = remember(nickName) { nickName?.let { UserTxtStorage.getUserByNick(context, it) } }
    val avatarName = remember { AvatarStorage.getAvatar(context) }
    val avatarResId = remember(avatarName) {
        context.resources.getIdentifier(avatarName, "drawable", context.packageName)
    }


    Surface(
        modifier = Modifier.fillMaxHeight(),
        color = Color(0xFFFFF9F0) // Fondo crema claro
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            // Encabezado del usuario
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = avatarResId),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(3.dp, Color(0xFFE94E36), CircleShape)
                        .shadow(4.dp, CircleShape)
                )


                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = user?.nickName ?: "Invitado",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = user?.email ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF707070)
                )

                Spacer(modifier = Modifier.height(5.dp))

                AppButton(
                    text = "👤 Mi perfil",
                    onClick = {
                        navigateWithDelay(scope, drawerState) {
                            navController.navigate("profile")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Divider(modifier = Modifier.padding(vertical = 10.dp), thickness = 6.dp)

            // Opciones de menú
            MenuSection(title = "Menú Principal") {

                DrawerItem("🏠 Home") {
                    navigateWithDelay(scope, drawerState) { navController.navigate("home") }
                }
                Spacer(modifier = Modifier.height(10.dp))
                DrawerItem("🍲 Recetas") {
                    navigateWithDelay(scope, drawerState) { navController.navigate("search") }
                }
                Spacer(modifier = Modifier.height(10.dp))
                DrawerItem("🎥 Videos") {
                    navigateWithDelay(scope, drawerState) { navController.navigate("videos") }
                }
                Spacer(modifier = Modifier.height(10.dp))
                DrawerItem("❤️ Favoritos") {
                    navigateWithDelay(scope, drawerState) { navController.navigate("favorites") }
                }
                Spacer(modifier = Modifier.height(10.dp))
                DrawerItem("🛍 Tiendas") {
                    navigateWithDelay(scope, drawerState) { navController.navigate("store") }
                }
                Spacer(modifier = Modifier.height(10.dp))

                //Button(onClick = { navController.navigate("web_browser") }) {
                    //Text("Navegar en Web")
                DrawerItem("🔍 Buscador Web") {
                    navigateWithDelay(scope, drawerState) { navController.navigate("web_browser") }
                }


            }

            Spacer(modifier = Modifier.weight(1f))

            Divider(modifier = Modifier.padding(vertical = 10.dp), thickness = 1.dp)

            DrawerItem("📕 Cerrar sesión") {
                scope.launch {
                    drawerState.close()
                    delay(250)
                    SessionManager.clearSession(context)
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            }
        }
    }
}

@Composable
fun MenuSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.padding(vertical = 8.dp)
    )
    Column(content = content)
}

fun navigateWithDelay(scope: CoroutineScope, drawerState: DrawerState, onNavigate: () -> Unit) {
    scope.launch {
        drawerState.close()
        delay(250)
        onNavigate()
    }
}
