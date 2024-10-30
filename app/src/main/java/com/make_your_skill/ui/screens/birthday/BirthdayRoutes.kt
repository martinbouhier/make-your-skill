package com.make_your_skill.ui.screens.birthday

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun BirthdayRoutes(
    navController: NavHostController,
    viewModel: BirthdayViewModel = viewModel()
){
    BirthdayScreen(navController)
}