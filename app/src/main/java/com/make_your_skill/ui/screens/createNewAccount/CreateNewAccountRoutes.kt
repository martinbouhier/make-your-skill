package com.make_your_skill.ui.screens.createNewAccount

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun CreateNewAccountRoutes(
    navController:NavHostController,
    viewModel: CreateNewAcoountViewModel = viewModel()
){
    CreateNewAccountScreen(navController = navController)
}