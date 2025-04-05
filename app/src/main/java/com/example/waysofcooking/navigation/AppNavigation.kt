package com.example.waysofcooking.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.waysofcooking.ui.screens.FavoritesScreen
import com.example.waysofcooking.ui.screens.HomeScreen
import com.example.waysofcooking.ui.screens.RecipeSearchScreen


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }

    // Agrega esta parte 👇
        composable("recipeSearch") {
            RecipeSearchScreen(navController)
        }
        composable("favorities"){
            FavoritesScreen(navController = navController)
        }
    }
}
