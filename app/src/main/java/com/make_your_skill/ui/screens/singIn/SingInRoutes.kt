package com.make_your_skill.ui.screens.singIn

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.make_your_skill.helpers.retrofit.auth.AuthRetrofit
import com.make_your_skill.helpers.retrofit.auth.GetAuthService


@Composable
fun SingInRoutes(
    navController: NavHostController,
    viewModel: SingInViewModel = viewModel(factory = SingInViewModel.provideFactory(GetAuthService(AuthRetrofit()))) //Aca estas dejando un Default muy complejo, pero ya esta arreglado.
){
    SignInScreen(navController = navController)
}

