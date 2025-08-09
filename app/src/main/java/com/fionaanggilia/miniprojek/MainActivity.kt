package com.fionaanggilia.miniprojek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fionaanggilia.miniprojek.ui.theme.MiniprojekTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MiniprojekTheme {
                // ✅ Pakai AndroidViewModelFactory
                val moodViewModel: MoodViewModel = viewModel(
                    factory = ViewModelProvider.AndroidViewModelFactory(application)
                )
                MoodlyApp(viewModel = moodViewModel)
            }
        }
    }
}

@Composable
fun MoodlyApp(viewModel: MoodViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }

        composable("moodSelection") {
            MoodSelectionScreen(navController)
        }

        composable("reason/{emojiType}") { backStackEntry ->
            val emojiType = backStackEntry.arguments?.getString("emojiType") ?: ""
            ReasonScreen(navController = navController, emojiType = emojiType, viewModel = viewModel)
        }

        composable("motivation/{mood}") { backStackEntry ->
            val mood = backStackEntry.arguments?.getString("mood") ?: "happy"
            MotivationScreen(navController = navController, mood = mood)
        }

        composable("moodWeek") {
            MoodWeekScreen(navController, viewModel)
        }

        composable("moodGraph") {
            MoodGraphScreen(viewModel = viewModel, navController = navController)
        }
    }
}
