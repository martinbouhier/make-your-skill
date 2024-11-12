package com.make_your_skill.ui.screens.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.make_your_skill.helpers.cookies.InMemoryCookieJar
import com.make_your_skill.ui.screens.singIn.SingInViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileRoutes(
    navController: NavHostController,
    singInViewModel: SingInViewModel,
    userId: Int,
    interestedSkillId: Int?,
    generateMatch: Boolean
) {
    ProfileScreen(
        navController = navController,
        singInViewModel = singInViewModel,
        userId = userId,
        interestedSkillId = interestedSkillId,
        generateMatch = generateMatch
    )
}