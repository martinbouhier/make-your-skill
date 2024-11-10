package com.make_your_skill.ui.screens.matchSearch

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.make_your_skill.ui.screens.singIn.SingInViewModel


@Composable
fun MatchSearchRoutes(
    navController: NavHostController,
    singInViewModel: SingInViewModel,
    matchSearchViewModel: MatchSearchViewModel,
    type: String
) {
    MatchSearchScreen(navController = navController,singInViewModel,matchSearchViewModel, type)
}
    