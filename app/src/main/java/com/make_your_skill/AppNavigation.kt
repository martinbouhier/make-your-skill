package com.make_your_skill

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.make_your_skill.ui.navigation.AppRoutes
import androidx.navigation.compose.composable
import com.make_your_skill.ui.components.BackButton
import com.make_your_skill.ui.components.BottomAppBarContent
import com.make_your_skill.ui.screens.birthday.BirthdayScreen
import com.make_your_skill.ui.screens.firstScreen.FirstScreenRoutes
import com.make_your_skill.ui.screens.mainScreen.MainScreenRoutes
import com.make_your_skill.ui.screens.matchSearch.MatchSearchRoutes
import com.make_your_skill.ui.screens.profile.ProfileRoutes
import com.make_your_skill.ui.screens.createNewAccount.CreateNewAccountRoutes
import com.make_your_skill.ui.screens.firstName.FirstNameScreen
import com.make_your_skill.ui.screens.interests.InterestedSkillsScreen
import com.make_your_skill.ui.screens.profileSettings.ProfileSettingsRoutes
import com.make_your_skill.ui.screens.singIn.SingInRoutes
import com.make_your_skill.ui.screens.skill.SkillsScreen
import com.make_your_skill.viewModel.AuthViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation(navController: NavHostController, authViewModel: AuthViewModel = viewModel()) {
    val isLoggedIn = authViewModel.isLoggedIn.value

    Scaffold(
        modifier = Modifier
            .padding(WindowInsets.statusBars.asPaddingValues()),
        topBar = {
            if(isLoggedIn){

                BackButton(navController, Color.Gray)
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
            composable(AppRoutes.FIRST_NAME_SCREEN){
                authViewModel.logout()
                FirstNameScreen(navController)
            }
            composable(AppRoutes.BIRTHDAY_SCREEN){
                authViewModel.logout()
                BirthdayScreen(navController)
            }
            composable(AppRoutes.SKILLS_SCREEN){
                authViewModel.logout()
                SkillsScreen(navController)
            }
            composable(AppRoutes.INTERESTS_SCREEN) {
                authViewModel.logout()
                InterestedSkillsScreen(navController)
            }
            composable(AppRoutes.SETTINGS_SCREEN){
                authViewModel.login()
                ProfileSettingsRoutes(navController = navController)

            }
        }
    }
}

