package com.make_your_skill

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import com.make_your_skill.ui.navigation.AppRoutes
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.make_your_skill.helpers.cookies.InMemoryCookieJar
import com.make_your_skill.ui.components.BottomAppBarContent
import com.make_your_skill.ui.components.buttons.BackButton
import com.make_your_skill.ui.screens.addSkillsAndInterests.AddSkillsAndInterestsRoutes
import com.make_your_skill.ui.screens.birthday.BirthdayScreen
import com.make_your_skill.ui.screens.firstScreen.FirstScreenRoutes
import com.make_your_skill.ui.screens.mainScreen.MainScreenRoutes
import com.make_your_skill.ui.screens.matchSearch.MatchSearchRoutes
import com.make_your_skill.ui.screens.profile.ProfileRoutes
import com.make_your_skill.ui.screens.createNewAccount.CreateNewAccountRoutes
import com.make_your_skill.ui.screens.createNewAccount.CreateNewAcoountViewModel
import com.make_your_skill.ui.screens.firstName.FirstNameScreen
import com.make_your_skill.ui.screens.interests.InterestedSkillsScreen
import com.make_your_skill.ui.screens.matchHistory.MatchHistoryRoutes
import com.make_your_skill.ui.screens.phoneNumber.PhoneNumberRoutes
import com.make_your_skill.ui.screens.profileSettings.ProfileSettingsRoutes
import com.make_your_skill.ui.screens.results.ResultsRoutes
import com.make_your_skill.ui.screens.searchForPaidClasses.SearchForPaidClassesScreen
import com.make_your_skill.ui.screens.singIn.SingInRoutes
import com.make_your_skill.ui.screens.singIn.SingInViewModel
import com.make_your_skill.ui.screens.skill.SkillsRoutes

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation(
    navController: NavHostController,
    singInViewModel: SingInViewModel = viewModel(),
    createNewAcoountViewModel: CreateNewAcoountViewModel = viewModel()
) {
    val cookieJar = InMemoryCookieJar()
    val isLoggedIn by singInViewModel.isLoggedIn.collectAsState()
    val userInfo by singInViewModel.signInInfo.collectAsState()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: AppRoutes.MAIN_SCREEN

    Scaffold(
        topBar = {
            if(currentRoute != AppRoutes.FIRST_SCREEN && currentRoute != AppRoutes.MAIN_SCREEN ){
                BackButton(navController, Color.Gray)
            }
        },
        bottomBar = {
            if(isLoggedIn){
                BottomAppBar(
                    containerColor = Color.White
                ) {
                    BottomAppBarContent(navController, userInfo!!.user.id)
                }
            }

        },
        modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = AppRoutes.FIRST_SCREEN,

            ) {
                composable(AppRoutes.FIRST_SCREEN) {
                    FirstScreenRoutes(navController)
                }
                composable(AppRoutes.LOGIN_SCREEN) {
                    SingInRoutes(navController, singInViewModel, cookieJar)
                }
                composable(AppRoutes.REGISTER_SCREEN) {
                    CreateNewAccountRoutes(navController, createNewAcoountViewModel)
                }
                composable(AppRoutes.MAIN_SCREEN) {
                    MainScreenRoutes(navController)
                }
                composable(
                    route = AppRoutes.PROFILE_SCREEN + "/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val userId: Int =
                        backStackEntry.arguments?.getString("userId")?.toIntOrNull() ?: 0
                    ProfileRoutes(navController, singInViewModel, cookieJar, userId)
                }
                composable(AppRoutes.MATCH_SEARCH_SCREEN) {
                    MatchSearchRoutes(navController)
                }
                composable(AppRoutes.ADD_SKILLS_INTEREST_SCREEN) {
                    AddSkillsAndInterestsRoutes(navController)
                }
                composable(AppRoutes.FIRST_NAME_SCREEN) {
                    FirstNameScreen(navController, createNewAcoountViewModel)
                }
                composable(AppRoutes.BIRTHDAY_SCREEN) {
                    BirthdayScreen(
                        navController,
                        createNewAcoountViewModel,
                        singInViewModel,
                        cookieJar
                    )
                }
                composable(
                    route = "${AppRoutes.SKILLS_SCREEN}?showContinue={showContinue}",
                    arguments = listOf(navArgument("showContinue") { type = NavType.StringType })
                ) { backStackEntry ->
                    val showContinue: Boolean =
                        backStackEntry.arguments?.getString("showContinue")?.toBoolean() ?: false
                    SkillsRoutes(navController, singInViewModel, cookieJar, showContinue)
                }
                composable(
                    route = "${AppRoutes.INTERESTS_SCREEN}?showContinue={showContinue}",
                    arguments = listOf(navArgument("showContinue") { type = NavType.StringType })
                ) {backStackEntry ->
                    val showContinue: Boolean =
                        backStackEntry.arguments?.getString("showContinue")?.toBoolean() ?: false
                    InterestedSkillsScreen(navController, singInViewModel, cookieJar, showContinue)
                }
                composable(AppRoutes.SETTINGS_SCREEN) {
                    ProfileSettingsRoutes(navController = navController)
                }
                composable(AppRoutes.RESULTS_SCREEN) {
                    ResultsRoutes(navController)
                }
                composable(AppRoutes.SEARCH_FOR_PAID_CLASSES_SCREEN) {
                    SearchForPaidClassesScreen(navController)
                }
                composable(AppRoutes.CELL_PHONE_SCREEN){
                    PhoneNumberRoutes(navController, createNewAcoountViewModel)
                }
                composable(AppRoutes.MATCH_HISTORY_SCREEN){
                    MatchHistoryRoutes(navController)
                }
            }
        }
    }
}

