package com.make_your_skill.ui.screens.singIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.make_your_skill.R
import com.make_your_skill.dataClasses.auth.body.SignInBody
import com.make_your_skill.ui.components.CustomButton
import com.make_your_skill.ui.components.TextInputLogin
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.theme.BackgroundColor2

@Composable
fun SignInScreen(navController: NavHostController) {
    val viewModel: SingInViewModel = viewModel()
    val isLoading by viewModel.loading.collectAsState()
    val signInInfo by viewModel.signInInfo.collectAsState()

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val onEmailChange: (String) -> Unit = { newEmail ->
        email.value = newEmail
    }

    val onPasswordChange: (String) -> Unit = { newPassword ->
        password.value = newPassword
    }

    val onClick = {
        val signInBody = SignInBody(email.value, password.value)
        viewModel.signIn(signInBody)
    }

    // Navegar a la pantalla principal cuando signInInfo no sea nulo
    LaunchedEffect(signInInfo) {
        if (signInInfo != null) {
            navController.navigate(AppRoutes.MAIN_SCREEN)
        }
    }

    val separation = 25.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor2)
            .padding(separation),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(screenHeight * 0.1f))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_purple),
                contentDescription = null,
                modifier = Modifier.size(78.dp),
                colorFilter = ColorFilter.tint(Color.White),
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Make Your Skill",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(60.dp))
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextInputLogin(label = "Email", text = email.value, onChange = onEmailChange)
            Spacer(modifier = Modifier.height(11.dp))
            TextInputLogin(label = "Password", isPassword = true, text = password.value, onChange = onPasswordChange)
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Forgot password",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Create new account",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }

        Column(
            modifier = Modifier.padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(
                onClick = { onClick() },
                text = if (isLoading) "Loading..." else "SIGN IN",
            )
        }
    }
}
