package com.make_your_skill

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.ui.screens.MainScreen
import com.make_your_skill.ui.screens.ProfileScreen
import com.make_your_skill.ui.screens.MatchSearchScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("Home") { MainScreen(navController) }
                composable("MatchSearch") { MatchSearchScreen(navController) }
                composable("Profile") { ProfileScreen() }
            }
        }
    }
}

