package com.make_your_skill.ui.screens.matchSearch

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun MatchSearchRoutes(
    navController: NavHostController,
    viewModel: MatchSearchViewModel = viewModel()
) {
    MatchSearchScreen(navController = navController)
}
    