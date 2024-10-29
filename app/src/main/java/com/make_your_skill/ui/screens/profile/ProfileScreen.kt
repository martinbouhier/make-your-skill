package com.make_your_skill.ui.screens.profile

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
import com.make_your_skill.ui.theme.*

@Composable
fun ProfileScreen(navController: NavHostController) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val spacerSeparation = 16.dp

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(screenHeight * 0.1f))
        Image(
            painter = painterResource(id = R.drawable.logo_purple),
            contentDescription = "App Logo",
            modifier = Modifier.size(128.dp)
        )
        Spacer(modifier = Modifier.height(spacerSeparation))
        Text(
            text = "Persona Random",
            style = styleTitle
        )
        Spacer(modifier = Modifier.height(spacerSeparation))
        Image(
            painter = painterResource(id = R.drawable.user_profile_icon),
            contentDescription = "User Profile Foto",
            modifier = Modifier.size(144.dp)
        )
        Spacer(modifier = Modifier.height(spacerSeparation))
        Text(
            text = "Age",
            style = styleSubtitle
        )
        Spacer(modifier = Modifier.height(spacerSeparation))
        Text(
            text = "Skills",
            style = styleSubtitle
        )
    }
}