package com.make_your_skill.ui.screens.firstName


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun firstNameRoute(
    navController: NavHostController,
    viewModel: FirstNameViewModel = viewModel()
){
    FirstNameScreen()
}

