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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    navController: NavHostController,
    drawerContent: @Composable (CoroutineScope, DrawerState) -> Unit = { _, _ -> },
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Generar el contenido del menú lateral una vez con los parámetros necesarios
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
        drawerContent = drawerContentFinal // ← ya es de tipo () -> Unit
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("100 Ways Of Cooking") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        TextButton(onClick = {
                            // Acción al login
                        }) {
                            Text("Login")
                        }
                    }
                )
            },
            content = content
        )
    }
}
