package com.example.waysofcooking.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.waysofcooking.ui.components.AppButton
import com.example.waysofcooking.data.SessionManager
import com.example.waysofcooking.data.UserPreferencesStorage

@Composable
fun EditProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    val nickName = remember { SessionManager.getLoggedUserNick(context) }

    var dieta by remember { mutableStateOf("") }
    var alergias by remember { mutableStateOf("") }

    // Cargar preferencias actuales al iniciar
    LaunchedEffect(nickName) {
        nickName?.let {
            val prefs = UserPreferencesStorage.getPreferences(context, it)
            if (prefs != null) {
                dieta = prefs["diet"] ?: ""
                alergias = prefs["allergies"] ?: ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Editar Perfil", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = dieta,
            onValueChange = { dieta = it },
            label = { Text("🍽️ Preferencias dietéticas") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = alergias,
            onValueChange = { alergias = it },
            label = { Text("⚠️ Alergias registradas") },
            modifier = Modifier.fillMaxWidth()
        )

        AppButton(
            text = "Guardar Cambios",
            onClick = {
                if (nickName != null) {
                    UserPreferencesStorage.savePreferences(context, nickName, dieta, alergias)
                    navController.popBackStack() // vuelve al perfil
                }
            }
        )

        AppButton(
            text = "Cancelar",
            onClick = {
                navController.popBackStack()
            }
        )
    }
}

