package com.make_your_skill.ui.screens.firstScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.R
import com.make_your_skill.ui.components.buttons.CustomButtonTransparent
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.theme.BackgroundColor2

@Composable
fun FirstScreenScreen(navController: NavHostController) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor2)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(150.dp))

        Image(
            painter = painterResource(id = R.drawable.logo_purple),
            contentDescription = null,
            modifier = Modifier.size(78.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Make Your Skill",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = "By tapping Create Account or Sign In, you agree to our Terms...",
            fontSize = 12.43.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 75.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomButtonTransparent(
            text = "Sign in",
            onClick = { navController.navigate(AppRoutes.LOGIN_SCREEN) },
            modifier = Modifier,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomButtonTransparent(
            text = "Create account",
            onClick = { navController.navigate(AppRoutes.REGISTER_SCREEN) },
            modifier = Modifier,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun FirstScreenScreenPreview() {
    FirstScreenScreen(navController = rememberNavController())
}
