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
import com.example.waysofcooking.ui.screens.VideoScreen
import com.example.waysofcooking.ui.screens.RecipeDetailScreen
import com.example.waysofcooking.ui.screens.SettingsScreen


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }

    // Bloque de navegacion
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
        composable("recipeDetail") {
            RecipeDetailScreen(navController = navController)
        }
        composable("allRecipes") {
            AllRecipesScreen(navController = navController)
        }
        composable("settings") {
            SettingsScreen(navController = navController)
        }

    }

}
