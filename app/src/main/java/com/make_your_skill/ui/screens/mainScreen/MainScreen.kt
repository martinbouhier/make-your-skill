package com.make_your_skill.ui.screens.mainScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.ui.screens.twoOptionScreen.TwoOptionsScreen
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.screens.search.MatchSearchViewModel
import com.make_your_skill.ui.screens.singIn.SingInViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    singInViewModel: SingInViewModel,
    matchSearchViewModel: MatchSearchViewModel
) {
    val listOfUserSkills by matchSearchViewModel.listOfUserSkills.collectAsState()
    val loadingUsersSearch by matchSearchViewModel.loadingUsersSearch.collectAsState()
    val errorUsersSearched by matchSearchViewModel.errorUsersSearched.collectAsState()

    LaunchedEffect(Unit) {
        val token = singInViewModel.getToken()
        val userId = singInViewModel.signInInfo.value!!.user.id
        matchSearchViewModel.getUserSkillByUserId(token,userId)
    }

    TwoOptionsScreen(navController = navController,
        firstButtonText = if (loadingUsersSearch) "Loading..."
                            else if (listOfUserSkills.isEmpty()) "MATCH (add some skills...)"
                            else "MATCH",
        secondButtonText = "SEARCH FOR PAID CLASSES",
        firstButtonAction = {
            if (listOfUserSkills.isNotEmpty()){
            navController.navigate(AppRoutes.MATCH_SEARCH_SCREEN + "?type=match")
        } else {
                navController.navigate(AppRoutes.SKILLS_SCREEN + "?showContinue=${false}")
        }
                            },
        secondButtonAction = { navController.navigate(AppRoutes.MATCH_SEARCH_SCREEN + "?type=paid") },
        description = ""
    )
}

