package com.make_your_skill.ui.screens.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.make_your_skill.R
import com.make_your_skill.ui.components.*
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.theme.*

@Composable
<<<<<<<< HEAD:app/src/main/java/com/make_your_skill/ui/screens/MyProfileScreen.kt
fun MyProfileScreen(modifier: Modifier = Modifier) {
========
fun MainScreen( navController: NavHostController) {
>>>>>>>> 960c1edd06e7053cf1d17ba0e81fb6f31984222a:app/src/main/java/com/make_your_skill/ui/screens/mainScreen/MainScreen.kt
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val separation = 16.dp

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
        CustomButton(onClick = { navController.navigate(AppRoutes.MatchSearchScreen) }, text = "MATCH")
        Spacer(modifier = Modifier.height(separation))
        Text(
            text = "OR",
            style = styleNormalText
            )
        Spacer(modifier = Modifier.height(separation))
        CustomButton(onClick = { /* TODO: Add action */ }, text = "SEARCH FOR PAID CLASSES")
    }
}