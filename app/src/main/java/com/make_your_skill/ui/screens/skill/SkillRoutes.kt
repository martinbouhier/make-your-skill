package com.make_your_skill.ui.screens.skill

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.make_your_skill.helpers.cookies.InMemoryCookieJar
import com.make_your_skill.ui.screens.singIn.SingInViewModel


@Composable
fun SkillsRoutes(
    navController: NavHostController,
    singInViewModel: SingInViewModel,
    cookieJar: InMemoryCookieJar,
    showContinue: Boolean
){
    SkillsScreen(navController, singInViewModel, cookieJar, showContinue)
}