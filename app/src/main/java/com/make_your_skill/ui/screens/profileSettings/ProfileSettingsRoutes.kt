package com.make_your_skill.ui.screens.profileSettings

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.make_your_skill.ui.screens.singIn.SingInViewModel

@Composable
fun ProfileSettingsRoutes(
    navController: NavHostController,
    singInViewModel: SingInViewModel
){
    ProfileSettingsScreen(navController,singInViewModel)
}