package com.make_your_skill.ui.screens.results

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ResultsRoutes(
    navController: NavHostController,
    viewModel: ResultsViewModel = viewModel()
) {
    ResultsScreen(navController = navController)
}