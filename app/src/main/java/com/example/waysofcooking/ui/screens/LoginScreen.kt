package com.example.waysofcooking.ui.screens

import androidx.compose.runtime.Composable
import com.example.waysofcooking.ui.components.DrawerMenuContent
import com.example.waysofcooking.ui.components.MainScaffold
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.waysofcooking.R
import androidx.navigation.NavHostController
import com.example.waysofcooking.data.UserTxtStorage
import com.example.waysofcooking.ui.components.MainScaffold
import com.example.waysofcooking.ui.components.DrawerMenuContent
import com.example.waysofcooking.ui.components.SimpleScaffold




@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginSuccess by remember { mutableStateOf<Boolean?>(null) }

    val context = LocalContext.current // ← esta línea debe estar aquí

    MainScaffold(
        navController = navController,
        drawerContent = { scope, drawerState ->
            DrawerMenuContent(scope = scope, drawerState = drawerState, navController = navController)
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        loginSuccess = UserTxtStorage.userExists(context, email, password)
                        if (loginSuccess == true) {
                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true } // para que no se regrese al loging
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Iniciar Sesión")
                }

                loginSuccess?.let {
                    Text(
                        if (it) "Inicio de sesión exitoso" else "Datos incorrectos",
                        color = if (it) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "¿No tienes cuenta?",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        navController.navigate("Register")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Regístrate aquí")
                }
            }
        }
    )
}
