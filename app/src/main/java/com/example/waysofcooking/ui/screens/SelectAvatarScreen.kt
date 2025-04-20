package com.example.waysofcooking.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.waysofcooking.ui.components.AppButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.waysofcooking.R
import com.example.waysofcooking.data.RecetasDataSource
import com.example.waysofcooking.ui.components.MainScaffold
import com.example.waysofcooking.ui.components.DrawerMenuContent
import androidx.compose.foundation.layout.Column

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.waysofcooking.data.AvatarStorage

@Composable
fun SelectAvatarScreen(navController: NavHostController) {
    val context = LocalContext.current
    val avatars = listOf(
        "avatar_1", "avatar_2", "avatar_3", "avatar_4",
        "avatar_5", "avatar_6", "avatar_7", "avatar_8",
        "avatar_robot"
    )

    MainScaffold(
        navController = navController,
        drawerContent = { scope, drawerState ->
            DrawerMenuContent(scope = scope, drawerState = drawerState, navController = navController)
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Selecciona tu avatar", style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(16.dp))

                LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                    items(avatars) { avatarName ->
                        val resId = context.resources.getIdentifier(avatarName, "drawable", context.packageName)
                        Image(
                            painter = painterResource(id = resId),
                            contentDescription = avatarName,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(8.dp)
                                .clickable {
                                    AvatarStorage.saveAvatar(context, avatarName)
                                    navController.popBackStack()
                                }
                        )
                    }
                }
            }
        }
    )
}
