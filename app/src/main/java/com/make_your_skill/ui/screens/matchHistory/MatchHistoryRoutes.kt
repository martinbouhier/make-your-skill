package com.make_your_skill.ui.screens.matchHistory

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.make_your_skill.ui.screens.singIn.SingInViewModel

@Composable
fun MatchHistoryRoutes(
    navController : NavHostController,
    singInViewModel: SingInViewModel
){
    MatchHistoryScreen(navController, singInViewModel)
}