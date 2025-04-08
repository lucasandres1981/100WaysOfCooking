package com.example.waysofcooking.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.waysofcooking.ui.screens.*
import androidx.navigation.navArgument
import com.example.waysofcooking.ui.screens.VideoScreen


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("login") {
            LoginScreen(navController = navController)
        }

        composable("register") {
            RegisterScreen(navController = navController)
        }

        composable("profile") {
            ProfileScreen(navController = navController)
        }

        composable("search") {
            RecipeSearchScreen(navController = navController)
        }

        composable("favorites") {
            FavoritesScreen(navController = navController)
        }

        composable("videos") {
            VideoScreen(navController = navController)
        }

        composable("store") {
            StoreScreen(navController = navController)
        }

        composable(
            route = "recipe_detail/{nombreId}",
            arguments = listOf(navArgument("nombreId") { defaultValue = "" })
        ) { backStackEntry ->
            val nombreId = backStackEntry.arguments?.getString("nombreId")
            RecipeDetailScreen(navController = navController, nombreId = nombreId)
        }

        composable("allRecipes") {
            AllRecipesScreen(navController = navController)
        }

        composable("settings") {
            SettingsScreen(navController = navController)
        }
    }
}
