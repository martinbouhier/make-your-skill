package com.make_your_skill

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.ui.screens.MainScreen
import com.make_your_skill.ui.screens.MyProfileScreen
import com.make_your_skill.ui.screens.MatchSearchScreen
import com.make_your_skill.ui.screens.ResultsScreen
import com.make_your_skill.ui.components.*
import java.util.Date

sealed class Screen(val route: String){
    object Home: Screen("Home")
    object MatchSearch: Screen("MatchSearch")
    object ProfileMatchList: Screen("ProfileMatchList")
    object ProfileSearchList: Screen("ProfileSearchList")
    object MyProfile: Screen("MyProfile")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable(Screen.Home.route) { MainScreen(navController) }
                composable(Screen.MatchSearch.route) { MatchSearchScreen(navController) }
                composable(Screen.ProfileMatchList.route) {
                    // Logica de acceso al back end para obtener la lista de perfiles

                    val sampleProfiles = listOf(
                        Profile("Juan Perez", "\$3000 x hora", "4.5", Date(2000, 5, 15)),
                        Profile("Julieta Diaz", "\$2500 x hora", "3.5", Date(1985, 8, 20))
                        )
                    val profileList = sampleProfiles
                    ResultsScreen(profileList)
                }
                composable(Screen.ProfileSearchList.route) {
                    // Logica de acceso al back end para obtener la lista de perfiles

                    val sampleProfiles = listOf(
                        Profile("Juan Perez", "\$3000 x hora", "4.5", Date(2000, 5, 15)),
                        Profile("Julieta Diaz", "\$2500 x hora", "3.5", Date(1985, 8, 20))
                    )
                    val profileList = sampleProfiles
                    ResultsScreen(profileList)
                }
                composable(Screen.MyProfile.route) { MyProfileScreen() }
            }
        }
    }
}

