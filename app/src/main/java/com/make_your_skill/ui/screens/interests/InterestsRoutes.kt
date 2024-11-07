package com.make_your_skill.ui.screens.interests

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.make_your_skill.helpers.cookies.InMemoryCookieJar
import com.make_your_skill.ui.screens.singIn.SingInViewModel


@Composable
fun InterestsRoutes(
    navController: NavHostController,
    singInViewModel: SingInViewModel,
    cookieJar: InMemoryCookieJar
){
    InterestedSkillsScreen(navController, singInViewModel, cookieJar)
}