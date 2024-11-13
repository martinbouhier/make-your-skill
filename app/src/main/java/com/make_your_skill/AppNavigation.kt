package com.make_your_skill

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
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
import androidx.navigation.NavType
import com.make_your_skill.ui.navigation.AppRoutes
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.make_your_skill.ui.components.BottomAppBarContent
import com.make_your_skill.ui.components.bars.CustomTopBar
import com.make_your_skill.ui.screens.addSkillsAndInterests.AddSkillsAndInterestsRoutes
import com.make_your_skill.ui.screens.birthday.BirthdayScreen
import com.make_your_skill.ui.screens.firstScreen.FirstScreenRoutes
import com.make_your_skill.ui.screens.mainScreen.MainScreenRoutes
import com.make_your_skill.ui.screens.search.MatchSearchRoutes
import com.make_your_skill.ui.screens.profile.ProfileRoutes
import com.make_your_skill.ui.screens.createNewAccount.CreateNewAccountRoutes
import com.make_your_skill.ui.screens.createNewAccount.CreateNewAcoountViewModel
import com.make_your_skill.ui.screens.firstName.FirstNameScreen
import com.make_your_skill.ui.screens.interests.InterestedSkillsScreen
import com.make_your_skill.ui.screens.matchHistory.MatchHistoryRoutes
import com.make_your_skill.ui.screens.search.MatchSearchViewModel
import com.make_your_skill.ui.screens.newPassword.NewPasswordRoute
import com.make_your_skill.ui.screens.phoneNumber.PhoneNumberRoutes
import com.make_your_skill.ui.screens.profileSettings.ProfileSettingsRoutes
import com.make_your_skill.ui.screens.results.ResultsRoutes
import com.make_your_skill.ui.screens.singIn.SingInRoutes
import com.make_your_skill.ui.screens.singIn.SingInViewModel
import com.make_your_skill.ui.screens.skill.SkillsRoutes

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation(
    navController: NavHostController,
    singInViewModel: SingInViewModel = viewModel(),
    createNewAcoountViewModel: CreateNewAcoountViewModel = viewModel(),
    matchSearchViewModel: MatchSearchViewModel = viewModel()
) {
    val isLoggedIn by singInViewModel.isLoggedIn.collectAsState()
    val userInfo by singInViewModel.signInInfo.collectAsState()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: AppRoutes.MAIN_SCREEN


    Log.d("currentRoute", currentRoute)


    Scaffold(
        topBar = {
            if(currentRoute != AppRoutes.FIRST_SCREEN && currentRoute != AppRoutes.MAIN_SCREEN ){
                val userId = if (currentRoute.startsWith(AppRoutes.PROFILE_SCREEN)) {
                    currentBackStackEntry?.arguments?.getString("userId")?.toIntOrNull() ?: 0
                } else null
                CustomTopBar(navController, currentRoute, (if(isLoggedIn){userInfo!!.user.id}else null),userId)
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
                    SingInRoutes(navController, singInViewModel)
                }
                composable(AppRoutes.REGISTER_SCREEN) {
                    CreateNewAccountRoutes(navController, createNewAcoountViewModel)
                }
                composable(AppRoutes.MAIN_SCREEN) {
                    MainScreenRoutes(navController, singInViewModel,matchSearchViewModel)
                }
                composable(
                    route = AppRoutes.PROFILE_SCREEN + "/{userId}?interestedSkillId={interestedSkillId}&generateMatch={generateMatch}",
                    arguments = listOf(
                        navArgument("userId") { type = NavType.StringType },
                        navArgument("interestedSkillId") {
                            type = NavType.StringType
                            nullable = true
                            defaultValue = null
                        },
                        navArgument("generateMatch") {
                            type = NavType.StringType
                            nullable = true
                            defaultValue = null
                        }
                    )
                ) { backStackEntry ->
                    val userId: Int = backStackEntry.arguments?.getString("userId")?.toIntOrNull() ?: 0
                    val interestedSkillId: Int? =
                        backStackEntry.arguments?.getString("interestedSkillId")?.toIntOrNull() ?: 0
                    val generateMatch: Boolean = backStackEntry.arguments?.getString("generateMatch")?.toBoolean() ?: false

                    ProfileRoutes(navController, singInViewModel, userId, interestedSkillId,generateMatch)
                }
                composable(
                    //type = match / paid
                    route = "${AppRoutes.MATCH_SEARCH_SCREEN}?type={type}",
                    arguments = listOf(navArgument("type") { type = NavType.StringType })
                ) {backStackEntry ->
                    val type: String =
                        backStackEntry.arguments?.getString("type") ?: "match"
                    MatchSearchRoutes(navController, singInViewModel, matchSearchViewModel,type)
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
                        singInViewModel
                    )
                }
                composable(
                    route = "${AppRoutes.SKILLS_SCREEN}?showContinue={showContinue}",
                    arguments = listOf(navArgument("showContinue") { type = NavType.StringType })
                ) { backStackEntry ->
                    val showContinue: Boolean =
                        backStackEntry.arguments?.getString("showContinue")?.toBoolean() ?: false
                    SkillsRoutes(navController, singInViewModel, showContinue)
                }
                composable(
                    route = "${AppRoutes.INTERESTS_SCREEN}?showContinue={showContinue}",
                    arguments = listOf(navArgument("showContinue") { type = NavType.StringType })
                ) {backStackEntry ->
                    val showContinue: Boolean =
                        backStackEntry.arguments?.getString("showContinue")?.toBoolean() ?: false
                    InterestedSkillsScreen(navController, singInViewModel, showContinue)
                }
                composable(AppRoutes.SETTINGS_SCREEN) {
                    ProfileSettingsRoutes(navController, singInViewModel)
                }
                composable(
                    //type = match / paid
                    route = "${AppRoutes.RESULTS_SCREEN}?type={type}",
                    arguments = listOf(navArgument("type") { type = NavType.StringType })
                ) {backStackEntry ->
                    val type: String =
                        backStackEntry.arguments?.getString("type") ?: "match"
                    ResultsRoutes(navController, matchSearchViewModel,singInViewModel,type)
                }
                composable(AppRoutes.CELL_PHONE_SCREEN){
                    PhoneNumberRoutes(navController, createNewAcoountViewModel)
                }
                composable(AppRoutes.MATCH_HISTORY_SCREEN){
                    MatchHistoryRoutes(navController, singInViewModel)
                }
                composable(AppRoutes.CHANGE_PASSWORD_SCREEN){
                    NewPasswordRoute(navController, singInViewModel)
                }
            }
        }
    }
}

