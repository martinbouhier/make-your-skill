package com.make_your_skill.ui.screens.mainScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun MainScreenRoutes(
    navController: NavHostController,
    viewModel: MainScreenViewModel = viewModel()
){
    MainScreen(navController)
}