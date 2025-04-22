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
import androidx.compose.ui.unit.dp
import android.content.SharedPreferences
import com.example.waysofcooking.data.AvatarStorage
import com.example.waysofcooking.data.UserPreferencesStorage
import com.example.waysofcooking.data.UserTxtStorage
import com.example.waysofcooking.ui.components.AppButton
import com.example.waysofcooking.ui.screens.EditProfileScreen



@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    val nickName = remember { SessionManager.getLoggedUserNick(context) }
    val user = remember(nickName) { nickName?.let { UserTxtStorage.getUserByNick(context, it) } }
    val preferences = remember(nickName) {nickName?.let {UserPreferencesStorage.getPreferences(context, it) }
    }


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

                val avatarResName = AvatarStorage.getAvatar(context)
                val avatarResId = remember(avatarResName) {
                    context.resources.getIdentifier(avatarResName, "drawable", context.packageName)
                }

                Image(
                    painter = painterResource(id = avatarResId),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(16.dp))

                AppButton(
                    text = "Cambiar avatar",
                    onClick = {
                        navController.navigate("select_avatar")
                    }
                )


                Spacer(modifier = Modifier.height(16.dp))

                user?.let {
                    Text(text = "👤 ${it.nickName}", fontWeight = FontWeight.Bold)
                    Text(text = "👤 ${it.fullName}", fontWeight = FontWeight.Bold)
                    Text(text = "✉️ ${it.email}")
                }

                Spacer(modifier = Modifier.height(24.dp))

                //Button(onClick = { /* Acción editar perfil */ }) {
                //    Text("Editar perfil")
                AppButton(
                    text = "Editar Perfil",
                    onClick = {
                        navController.navigate("select_avatar")
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "🍽️ Preferencias dietéticas: ${preferences?.get("diet") ?: "No registradas"}")
                Text(text = "⚠️ Alergias registradas: ${preferences?.get("allergies") ?: "Ninguna"}")
                Text(text = "❤️ Mis Recetas Favoritas")

                Spacer(modifier = Modifier.height(24.dp))

                AppButton(
                    text = "Editar Preferencias",
                    onClick = {
                        navController.navigate("edit_profile")
                    }
                )


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


