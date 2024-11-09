package com.make_your_skill.ui.screens.singIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.make_your_skill.R
import com.make_your_skill.helpers.cookies.InMemoryCookieJar
import com.make_your_skill.ui.components.buttons.CustomButton
import com.make_your_skill.ui.components.text.textFileds.TextInputLogin
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.theme.BackgroundColor2

@Composable
fun SignInScreen(
    navController: NavHostController,
    singInViewModel: SingInViewModel
) {
    val isLoading by singInViewModel.loading.collectAsState()
    val signInInfo by singInViewModel.signInInfo.collectAsState()
    val email by singInViewModel.email.collectAsState()
    val password by singInViewModel.password.collectAsState()
    val error by singInViewModel.error.collectAsState()

    val separation = 25.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    // Navegar a la pantalla principal cuando signInInfo no sea nulo
    LaunchedEffect(signInInfo) {
        if (signInInfo != null) {
            singInViewModel.setIsLoggedIn(true)
            navController.navigate(AppRoutes.MAIN_SCREEN)
        }
    }

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
            TextInputLogin(label = "Email", text = email, onChange = singInViewModel.onEmailChange, error = error)
            Spacer(modifier = Modifier.height(11.dp))
            TextInputLogin(label = "Password", isPassword = true, text = password, onChange = singInViewModel.onPasswordChange,error = error)
            Spacer(modifier = Modifier.height(16.dp))
            if (error != null){
                Text(
                    text = error.toString(),
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "Forgot password",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Create new account",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                modifier = Modifier
                    .clickable { navController.navigate(AppRoutes.REGISTER_SCREEN) }
            )
        }

        Column(
            modifier = Modifier.padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(
                onClick = { singInViewModel.onLogin() },
                text = if (isLoading) "Loading..." else "SIGN IN",
            )
        }
    }
}
