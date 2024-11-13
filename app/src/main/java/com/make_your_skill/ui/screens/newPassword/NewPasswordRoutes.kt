package com.make_your_skill.ui.screens.newPassword

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.make_your_skill.ui.screens.createNewAccount.CreateNewAcoountViewModel
import com.make_your_skill.ui.screens.firstName.FirstNameScreen
import com.make_your_skill.ui.screens.singIn.SingInViewModel

@Composable
fun NewPasswordRoute(
    navController: NavHostController,
    singInViewModel: SingInViewModel
){
    NewPasswordScreen(
        navController = navController,
        singInViewModel = singInViewModel
    )
}