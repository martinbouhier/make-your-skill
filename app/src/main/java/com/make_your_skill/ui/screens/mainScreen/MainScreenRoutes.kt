package com.make_your_skill.ui.screens.mainScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.make_your_skill.ui.screens.search.MatchSearchViewModel
import com.make_your_skill.ui.screens.singIn.SingInViewModel


@Composable
fun MainScreenRoutes(
    navController: NavHostController,
    singInViewModel: SingInViewModel,
    matchSearchViewModel: MatchSearchViewModel
) {
    MainScreen(
        navController = navController,
        singInViewModel = singInViewModel,
        matchSearchViewModel = matchSearchViewModel)
}