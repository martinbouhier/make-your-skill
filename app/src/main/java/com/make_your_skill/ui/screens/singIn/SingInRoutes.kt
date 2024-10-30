package com.make_your_skill.ui.screens.singIn

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun SingInRoutes(
    navController: NavHostController,
    viewModel: SingInViewModel = viewModel()
){
    SignInScreen(navController = navController)
}

