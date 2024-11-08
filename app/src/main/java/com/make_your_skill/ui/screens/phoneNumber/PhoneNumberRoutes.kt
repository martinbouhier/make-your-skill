package com.make_your_skill.ui.screens.phoneNumber

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.make_your_skill.helpers.cookies.InMemoryCookieJar
import com.make_your_skill.ui.screens.createNewAccount.CreateNewAcoountViewModel
import com.make_your_skill.ui.screens.profile.ProfileScreen
import com.make_your_skill.ui.screens.singIn.SingInViewModel

@Composable
fun ProfileRoutes(
    navController: NavHostController,
    createNewAcoountViewModel: CreateNewAcoountViewModel
) {
    PhoneNumberScreen(navController = navController, createNewAcoountViewModel = createNewAcoountViewModel)
}