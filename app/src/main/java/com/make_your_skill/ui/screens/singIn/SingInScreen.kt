package com.make_your_skill.ui.screens.singIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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

    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }

    // Navegar a la pantalla principal cuando signInInfo no sea nulo
    LaunchedEffect(signInInfo) {
        if (signInInfo != null) {
            singInViewModel.setIsLoggedIn(true)
            navController.navigate(AppRoutes.MAIN_SCREEN){
                popUpTo(0)
            }
        }
    }

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val GAP = 25.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor2)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))
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

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                TextInputLogin(
                    label = "Email",
                    text = email,
                    onChange = singInViewModel.onEmailChange,
                    error = error,
                    focusRequester = focusRequester1,
                    nextFocusRequester = focusRequester2
                )
                Spacer(modifier = Modifier.height(10.dp))

                TextInputLogin(
                    label = "Password",
                    isPassword = true,
                    text = password,
                    onChange = singInViewModel.onPasswordChange,
                    error = error,
                    focusRequester = focusRequester2,
                    onImeAction = {singInViewModel.onLogin()}
                )

                if (error != null){
                    Text(
                        text = error.toString(),
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Forgot password",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Create new account",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    modifier = Modifier
                        .clickable { navController.navigate(AppRoutes.REGISTER_SCREEN) }
                )
            }
        }

        Column(
            modifier = Modifier.padding(bottom = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(
                onClick = { singInViewModel.onLogin() },
                text = if (isLoading) "Loading..." else "SIGN IN",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen(rememberNavController(), hiltViewModel())
}
//
