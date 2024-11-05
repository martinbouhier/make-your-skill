package com.make_your_skill.ui.screens.firstName


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.make_your_skill.ui.screens.createNewAccount.CreateNewAcoountViewModel

@Composable
fun firstNameRoute(
    navController: NavHostController,
    createNewAcoountViewModel: CreateNewAcoountViewModel = viewModel()
){
    FirstNameScreen(
        navController = navController,
        createNewAcoountViewModel = createNewAcoountViewModel
    )
}

