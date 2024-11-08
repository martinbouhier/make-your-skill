package com.make_your_skill.ui.screens.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.R
import com.make_your_skill.ui.components.*
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.theme.*

@Composable
fun MainScreen( navController: NavHostController) {
    TwoOptionsScreen(navController = navController,
        firstButtonText = "MATCH",
        secondButtonText = "SEARCH FOR PAID CLASSES",
        firstButtonAction = { navController.navigate(AppRoutes.MATCH_SEARCH_SCREEN) },
        secondButtonAction = { navController.navigate(AppRoutes.SEARCH_FOR_PAID_CLASSES_SCREEN) },
        description = ""
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    val navController = rememberNavController()
    MainScreen(navController)
}

