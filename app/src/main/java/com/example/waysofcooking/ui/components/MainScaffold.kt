package com.example.waysofcooking.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.waysofcooking.data.SessionManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    navController: NavHostController,
    drawerContent: @Composable (CoroutineScope, DrawerState) -> Unit = { _, _ -> },
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Generar el contenido del menú lateral una vez con los parámetros necesarios si hay que me ter mas se mira mas tarde por el momento dejar asi
    val drawerContentFinal = @Composable {
        Surface(
            modifier = Modifier.width(280.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            drawerContent(scope, drawerState)
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = drawerContentFinal // ya es de tipo () -> Unit
    ) {
        val context = LocalContext.current
        val loggednickName = remember { SessionManager.getLoggedUserNick(context) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "100 Ways Of Cooking",
                            style = MaterialTheme.typography.titleLarge,
                            //color = MaterialTheme.
                            color = Color.White
                        )
                    },
                    //colors = TopAppBarDefaults.topAppBarColors(
                    //    containerColor = MaterialTheme.colorScheme.primary,
                    //    titleContentColor = MaterialTheme.colorScheme.onPrimary
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White,
                        actionIconContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        if (loggednickName != null) {
                            Text(
                                text = loggednickName,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(end = 16.dp)
                            )
                        } else {
                            TextButton(onClick = {
                                navController.navigate("login")
                            }) {
                                Text(
                                    text = "Login",
                                    style = MaterialTheme.typography.bodyMedium,
                                    //color = MaterialTheme.colorScheme.onPrimary,
                                    color = Color.White,
                                    modifier = Modifier.padding(end = 18.dp)
                                )
                            }
                        }
                    }
                )
            },
            content = content
        )
    }
}
