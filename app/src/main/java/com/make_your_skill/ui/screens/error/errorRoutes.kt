package com.make_your_skill.ui.screens.error


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun errorRoute(
    navController: NavHostController,
    viewModel: ErrorViewModel = viewModel()
){
    ErrorScreen(navController)
}

