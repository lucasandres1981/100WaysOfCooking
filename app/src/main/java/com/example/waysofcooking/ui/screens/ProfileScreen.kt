package com.example.waysofcooking.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.waysofcooking.ui.components.DrawerMenuContent
import com.example.waysofcooking.ui.components.MainScaffold
import java.io.File
import com.example.waysofcooking.data.SessionManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.CircleShape
import com.example.waysofcooking.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import android.content.SharedPreferences
import com.example.waysofcooking.data.UserTxtStorage

@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    val email = remember { SessionManager.getLoggedUserEmail(context) }
    val user = remember(email) { email?.let { it: String -> UserTxtStorage.getUserByEmail(context, it) } }


    MainScaffold(
        navController = navController,
        drawerContent = { scope, drawerState ->
            DrawerMenuContent(scope = scope, drawerState = drawerState, navController = navController)
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Perfil ⚙️",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.lucas),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(16.dp))

                user?.let {
                    Text(text = "👤 ${it.fullName}", fontWeight = FontWeight.Bold)
                    Text(text = "✉️ ${it.email}")
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = { /* Acción editar perfil */ }) {
                    Text("Editar perfil")
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "🍽️ Preferencias dietéticas: Vegetariano")
                Text(text = "⚠️ Alergias registradas: Mariscos")
                Text(text = "❤️ Mis Recetas Favoritas")

                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = { /* Acción editar preferencias */ }) {
                    Text("Editar preferencias")
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = {
                    SessionManager.clearSession(context)
                    navController.navigate("login") {
                        popUpTo("profile") { inclusive = true }
                    }
                }) {
                    Text("Cerrar sesión")
                }
            }
        }
    )
}


fun readLastLoggedUser(context: Context): Pair<String, String>? { // Esta función obtiene la última línea del archivo usuarios.txt y devuelve el nombre y el email
    val file = File(context.filesDir, "usuarios.txt")
    if (!file.exists()) return null

    val lines = file.readLines()
    val lastLine = lines.lastOrNull() ?: return null
    val parts = lastLine.split(",")
    return if (parts.size >= 2) Pair(parts[0], parts[1]) else null
}
