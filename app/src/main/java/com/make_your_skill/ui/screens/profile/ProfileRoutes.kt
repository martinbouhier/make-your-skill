package com.make_your_skill.ui.screens.profile

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.make_your_skill.helpers.cookies.InMemoryCookieJar
import com.make_your_skill.ui.screens.singIn.SingInViewModel

@Composable
fun ProfileRoutes(
    navController: NavHostController,
    singInViewModel: SingInViewModel = viewModel(),
    cookieJar: InMemoryCookieJar
) {
    ProfileScreen(
        navController = navController,
        singInViewModel = singInViewModel,
        cookieJar = cookieJar
    )
}