package com.make_your_skill

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.make_your_skill.ui.screens.createNewAccount.CreateNewAcoountViewModel
import com.make_your_skill.ui.screens.firstName.FirstNameScreen
import com.make_your_skill.ui.screens.interests.InterestedSkillsScreen
import com.make_your_skill.ui.screens.profileSettings.ProfileSettingsRoutes
import com.make_your_skill.ui.screens.results.ResultsRoutes
import com.make_your_skill.ui.screens.searchForPaidClasses.SearchForPaidClassesScreen
import com.make_your_skill.ui.screens.singIn.SingInRoutes
import com.make_your_skill.ui.screens.singIn.SingInViewModel
import com.make_your_skill.ui.screens.skill.SkillsScreen
import com.make_your_skill.viewModel.MakeYourSkillViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation(
    navController: NavHostController,
    singInViewModel: SingInViewModel = viewModel(),
    createNewAcoountViewModel: CreateNewAcoountViewModel = viewModel()
) {
    val isLoggedIn by singInViewModel.isLoggedIn.collectAsState()

    Scaffold(
        modifier = Modifier
            .padding(WindowInsets.statusBars.asPaddingValues()),
        topBar = {
            if(true){
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
                FirstScreenRoutes(navController)
            }
            composable(AppRoutes.LOGIN_SCREEN){
                SingInRoutes(navController,singInViewModel)
            }
            composable(AppRoutes.REGISTER_SCREEN){
                CreateNewAccountRoutes(navController, createNewAcoountViewModel)
            }
            composable(AppRoutes.MAIN_SCREEN) {
                MainScreenRoutes(navController)
            }
            composable(AppRoutes.PROFILE_SCREEN) {
                ProfileRoutes(navController)
            }
            composable(AppRoutes.MATCH_SEARCH_SCREEN){
                MatchSearchRoutes(navController)
            }
            composable(AppRoutes.FIRST_NAME_SCREEN){
                FirstNameScreen(navController, createNewAcoountViewModel)
            }
            composable(AppRoutes.BIRTHDAY_SCREEN){
                BirthdayScreen(navController, createNewAcoountViewModel, singInViewModel)
            }
            composable(AppRoutes.SKILLS_SCREEN){
                SkillsScreen(navController, singInViewModel)
            }
            composable(AppRoutes.INTERESTS_SCREEN) {
                InterestedSkillsScreen(navController)
            }
            composable(AppRoutes.SETTINGS_SCREEN){
                ProfileSettingsRoutes(navController = navController)
            }
            composable(AppRoutes.RESULTS_SCREEN){
                ResultsRoutes(navController)
            }
            composable(AppRoutes.SEARCH_FOR_PAID_CLASSES_SCREEN){
                SearchForPaidClassesScreen(navController)
            }
        }
    }
}

