package com.make_your_skill.ui.screens.birthday

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.make_your_skill.helpers.cookies.InMemoryCookieJar
import com.make_your_skill.ui.screens.createNewAccount.CreateNewAcoountViewModel
import com.make_your_skill.ui.screens.singIn.SingInViewModel

@Composable
fun BirthdayRoutes(
    navController: NavHostController,
    createNewAcoountViewModel: CreateNewAcoountViewModel = viewModel(),
    singInViewModel: SingInViewModel = viewModel(),
    cookieJar: InMemoryCookieJar
){
    BirthdayScreen(
        navController = navController,
        createNewAcoountViewModel = createNewAcoountViewModel,
        singInViewModel = singInViewModel,
        cookieJar = cookieJar
    )
}