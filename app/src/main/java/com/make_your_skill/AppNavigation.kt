package com.make_your_skill

import android.annotation.SuppressLint
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.make_your_skill.ui.navigation.AppRoutes
import androidx.navigation.compose.composable
import com.make_your_skill.ui.components.BackButton
import com.make_your_skill.ui.components.BottomAppBarContent
import com.make_your_skill.ui.screens.FirstScreen.FirstScreenRoutes
import com.make_your_skill.ui.screens.MainScreen.MainScreenRoutes
import com.make_your_skill.ui.screens.MatchSearchScreen.MatchSearchRoutes
import com.make_your_skill.ui.screens.Profile.ProfileRoutes
import com.make_your_skill.ui.screens.createNewAccount.CreateNewAccountRoutes
import com.make_your_skill.ui.screens.singIn.SingInRoutes
import com.make_your_skill.viewModel.AuthViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation(navController: NavHostController, authViewModel: AuthViewModel = viewModel()) {
    val isLoggedIn = authViewModel.isLoggedIn.value

    Scaffold(

        topBar = {
            if(isLoggedIn){
                BackButton(navController, Color.White)
            }

        },
        bottomBar = {
            if(isLoggedIn){
                BottomAppBar(
                    containerColor = Color.White
                ) {
                    BottomAppBarContent(navController)
                }
            }

        }
    ) {
        NavHost(
            navController = navController,
            startDestination = AppRoutes.FIRST_SCREEN
        ) {
            composable(AppRoutes.FIRST_SCREEN){
                authViewModel.logout()
                FirstScreenRoutes(navController)
            }
            composable(AppRoutes.LOGIN_SCREEN){
                authViewModel.logout()
                SingInRoutes(navController)
            }
            composable(AppRoutes.REGISTER_SCREEN){
                authViewModel.logout()
                CreateNewAccountRoutes(navController)
            }
            composable(AppRoutes.MAIN_SCREEN) {
                authViewModel.login()
                MainScreenRoutes(navController)
            }
            composable(AppRoutes.PROFILE_SCREEN) {
                authViewModel.login()
                ProfileRoutes(navController)
            }
            composable(AppRoutes.MatchSearchScreen){
                authViewModel.login()
                MatchSearchRoutes(navController)
            }

        }
    }
}

