package com.make_your_skill.ui.screens.profile

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProfileRoutes(
    navController: NavHostController,
    viewModel: ProfileViewModel = viewModel()
) {
    ProfileScreen(navController = navController)
}