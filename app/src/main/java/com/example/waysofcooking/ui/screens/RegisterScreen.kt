package com.example.waysofcooking.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.waysofcooking.data.User
import com.example.waysofcooking.data.UserRepository
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.waysofcooking.R
import com.example.waysofcooking.data.UserTxtStorage


@Composable
fun RegisterScreen(navController: NavHostController) {
    var nickName by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var registrationSuccess by remember { mutableStateOf<Boolean?>(null) }
    var invalidEmail by remember { mutableStateOf(false) } // Nueva bandera
    val context = LocalContext.current

    fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".") && email.length > 5
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
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo de 100 Ways of Cooking",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .height(200.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(30.dp))

                OutlinedTextField(
                    value = nickName,
                    onValueChange = { nickName = it },
                    label = { Text("User or Nick") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        invalidEmail = false // reset cuando cambia
                    },
                    label = { Text("Email Address") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = invalidEmail
                )
                if (invalidEmail) {
                    Text(
                        text = "Por favor, introduce un correo válido",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (!isValidEmail(email)) {
                            invalidEmail = true
                            registrationSuccess = false
                        } else if (password == confirmPassword) {
                            val user = User(nickName, fullName, email, password)
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
                    } else if (!invalidEmail) {
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
