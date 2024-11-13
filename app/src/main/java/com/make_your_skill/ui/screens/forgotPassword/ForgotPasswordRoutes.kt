package com.make_your_skill.ui.screens.forgotPassword

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.make_your_skill.ui.screens.singIn.SingInViewModel

@Composable
fun ForgotPasswordRoute(
    navController: NavHostController,
){
    ForgotPasswordScreen(
        navController = navController
    )
}