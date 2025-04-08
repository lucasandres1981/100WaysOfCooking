package com.example.waysofcooking.ui.screens

import androidx.compose.runtime.Composable
import com.example.waysofcooking.ui.components.DrawerMenuContent
import com.example.waysofcooking.ui.components.MainScaffold
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.waysofcooking.data.User
import com.example.waysofcooking.data.UserRepository
import androidx.compose.ui.platform.LocalContext
import com.example.waysofcooking.data.UserTxtStorage


@Composable
fun RegisterScreen(navController: NavHostController) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var registrationSuccess by remember { mutableStateOf<Boolean?>(null) }
    val context = LocalContext.current


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
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email Address") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (password == confirmPassword) {
                            val user = User(fullName, email, password)
                            registrationSuccess = UserTxtStorage.saveUser(context, user)
                        } else {
                            registrationSuccess = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Create Account")
                }
                registrationSuccess?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    if (it) {
                        Text(
                            "Usuario registrado correctamente",
                            color = MaterialTheme.colorScheme.primary
                        )
                    } else {
                        Text(
                            "Error al registrar usuario. Verifica que no esté repetido o las contraseñas.",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    )
}
