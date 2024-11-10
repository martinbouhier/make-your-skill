package com.make_your_skill.ui.screens.mainScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.ui.screens.twoOptionScreen.TwoOptionsScreen
import com.make_your_skill.ui.navigation.AppRoutes

@Composable
fun MainScreen( navController: NavHostController) {
    TwoOptionsScreen(navController = navController,
        firstButtonText = "MATCH",
        secondButtonText = "SEARCH FOR PAID CLASSES",
        firstButtonAction = { navController.navigate(AppRoutes.MATCH_SEARCH_SCREEN + "?type=match") },
        secondButtonAction = { navController.navigate(AppRoutes.MATCH_SEARCH_SCREEN + "?type=paid") },
        description = ""
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    val navController = rememberNavController()
    MainScreen(navController)
}

