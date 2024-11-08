package com.make_your_skill.ui.screens.singIn

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.make_your_skill.helpers.cookies.InMemoryCookieJar


@Composable
fun SingInRoutes(
    navController: NavHostController,
    singInViewModel: SingInViewModel,
    cookieJar: InMemoryCookieJar
){
    SignInScreen(navController = navController,cookieJar = cookieJar, singInViewModel = singInViewModel)
}

