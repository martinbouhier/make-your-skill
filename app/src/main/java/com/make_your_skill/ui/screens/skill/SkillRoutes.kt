package com.make_your_skill.ui.screens.skill

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.make_your_skill.ui.screens.birthday.BirthdayScreen
import com.make_your_skill.ui.screens.birthday.BirthdayViewModel

@Composable
fun SkillsRoutes(
    navController: NavHostController,
    viewModel: SkillsViewModel = viewModel()
){
    SkillsScreen(navController)
}