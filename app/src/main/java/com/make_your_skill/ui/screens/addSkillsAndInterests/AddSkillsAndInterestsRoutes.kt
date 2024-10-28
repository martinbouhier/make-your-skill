package com.make_your_skill.ui.screens.addSkillsAndInterests

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AddSkillsAndInterestsRoutes(
    navController: NavHostController,
    viewModel: AddSkillsAndInterestsViewModel = viewModel()
){
    AddSkillsAndInterestsScreen(navController)
}