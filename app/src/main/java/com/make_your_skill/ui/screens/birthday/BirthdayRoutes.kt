package com.make_your_skill.ui.screens.birthday

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.make_your_skill.ui.screens.createNewAccount.CreateNewAcoountViewModel

@Composable
fun BirthdayRoutes(
    navController: NavHostController,
    createNewAcoountViewModel: CreateNewAcoountViewModel = viewModel()
){
    BirthdayScreen(
        navController = navController,
        createNewAcoountViewModel = createNewAcoountViewModel
    )
}