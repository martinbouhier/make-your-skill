package com.make_your_skill.ui.screens.firstScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun FirstScreenRoutes(
    navController: NavHostController,
    viewModel: FirstScreenViewModel = viewModel()
){
    FirstScreenScreen(navController = navController)
}