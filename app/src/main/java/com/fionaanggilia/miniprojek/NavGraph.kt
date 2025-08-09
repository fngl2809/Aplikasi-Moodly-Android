package com.fionaanggilia.miniprojek

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(navController: NavHostController, viewModel: MoodViewModel) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("moodSelection") { MoodSelectionScreen(navController) }
        composable("reason/{mood}") { backStackEntry ->
            val mood = backStackEntry.arguments?.getString("mood") ?: ""
            ReasonScreen(navController, mood, viewModel) // ✅ FIXED
        }
        composable("motivation/{mood}") { backStackEntry ->
            val mood = backStackEntry.arguments?.getString("mood") ?: "happy"
            MotivationScreen(navController, mood)
        }
        composable("moodWeek") {
            MoodWeekScreen(navController, viewModel) // ✅ FIXED
        }
        composable("moodGraph") {
            MoodGraphScreen(viewModel, navController)
        }
    }
}
