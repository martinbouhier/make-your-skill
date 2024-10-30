package com.make_your_skill.ui.screens.interests

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController


@Composable
fun InterestsRoutes(
    navController: NavHostController,
    viewModel: InterestsViewModel = viewModel()
){
    InterestedSkillsScreen(navController)
}