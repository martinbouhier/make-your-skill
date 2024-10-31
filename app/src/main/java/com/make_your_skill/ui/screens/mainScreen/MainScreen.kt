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
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val separation = 16.dp

    val FIRST_BUTTON = "MATCH"
    val SECOND_BUTTON = "SEARCH FOR PAID CLASSES"
    val SCREEN_TEXT = "OR"

    Column(
        modifier = Modifier.fillMaxSize().padding(separation),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(screenHeight * 0.1f))
        Image(
            painter = painterResource(id = R.drawable.logo_purple),
            contentDescription = "App Logo",
            modifier = Modifier.size(128.dp)
        )
        Spacer(modifier = Modifier.height(160.dp))
        CustomButton(onClick = { navController.navigate(AppRoutes.MATCH_SEARCH_SCREEN) },
            text = FIRST_BUTTON)
        Spacer(modifier = Modifier.height(separation))
        Text(
            text = SCREEN_TEXT,
            style = styleNormalText
            )
        Spacer(modifier = Modifier.height(separation))
        CustomButton(onClick = { navController.navigate(AppRoutes.SEARCH_FOR_PAID_CLASSES_SCREEN) },
            text = SECOND_BUTTON)
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    val navController = rememberNavController()
    MainScreen(navController)
}

