package com.example.waysofcooking.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.waysofcooking.ui.screens.HomeScreen
import com.example.waysofcooking.ui.screens.RecipeSearchScreen
import com.example.waysofcooking.ui.screens.AllRecipesScreen
import com.example.waysofcooking.ui.screens.FavoritesScreen
import com.example.waysofcooking.ui.screens.LoginScreen
import com.example.waysofcooking.ui.screens.ProfileScreen
import com.example.waysofcooking.ui.screens.RegisterScreen
import com.example.waysofcooking.ui.screens.StoreScreen
import VideoScreen
import com.example.waysofcooking.ui.screens.RecipeDetailScreen
import com.example.waysofcooking.ui.screens.SettingsScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }

    // Bloque de navegacion

        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("register") {
            RegisterScreen(navController = navController)
        }
        composable("profile") {
            ProfileScreen(navController = navController)
        }
        composable("menu") {
            // Si existe una MenuScreen, a
            // MenuScreen(navController = navController)
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
        composable("recipeDetail/{nombreId}") { backStackEntry ->
            val nombreId = backStackEntry.arguments?.getString("nombreId")
            RecipeDetailScreen(navController = navController, nombreId = nombreId)
        }
        composable("allRecipes") {
            AllRecipesScreen(navController = navController)
        }
        composable("settings") {
            SettingsScreen(navController = navController)
        }
        composable(
            "recipe_detail/{nombreId}",
            arguments = listOf(navArgument("nombreId") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombreId = backStackEntry.arguments?.getString("nombreId")
            RecipeDetailScreen(navController, nombreId)
        }

    }

}
