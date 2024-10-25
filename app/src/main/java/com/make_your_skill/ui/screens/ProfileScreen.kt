package com.make_your_skill.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.make_your_skill.R

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val spacerSeparation = 16.dp
    val styleTitle = TextStyle(
        fontFamily =  FontFamily.SansSerif,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xF55451DC)
    )
    val styleSubtitle = TextStyle(
        fontSize = 19.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xF55451DC)
    )

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
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