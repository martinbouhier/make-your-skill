package com.make_your_skill.ui.screens.profileSettings

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProfileSettingsRoutes(
    navController: NavHostController,
    viewModel: ProfileSettingsViewModel = viewModel()
){
    ProfileSettingsScreen(navController)
}