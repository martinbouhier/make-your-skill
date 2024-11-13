package com.make_your_skill.ui.screens.forgotPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.dataClasses.forgotPassword.ForgotPasswordDataClass
import com.make_your_skill.ui.components.buttons.CustomButton
import com.make_your_skill.ui.components.buttons.CustomTextField
import com.make_your_skill.ui.components.text.ScreenTitleText
import com.make_your_skill.ui.navigation.AppRoutes

@Composable
fun ForgotPasswordScreen(
    navController: NavHostController
){
    val forgotPasswordViewModel: ForgotPasswordViewModel = viewModel()
    val separation = 25.dp
    val email by forgotPasswordViewModel.emailForgotPassword.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(separation),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth() // Ocupa todo el ancho
                    .fillMaxHeight(0.7f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ScreenTitleText("Forgot Password")
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "You will receive a new password via email",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                CustomTextField(
                    text = email ?: "",
                    label = "Email...",
                    isNumericOnly = false,
                    maxLength = 50,
                    onSubmit = {},
                    isSubmitEnabled = true,
                    onTextChange= { newValue ->
                        forgotPasswordViewModel.onEmailChange(newValue)
                    }
                )

            }
        }
        Row {
            CustomButton({
                val forgotPasswordDataClass = ForgotPasswordDataClass(to = email!!)
                forgotPasswordViewModel.sendEmailPassword(forgotPasswordDataClass) { success ->
                    if (success) {
                        navController.navigate(AppRoutes.LOGIN_SCREEN)
                    } else {
                        // Handle failure (e.g., show an error message)
                    }
                }
            }, "Send")
        }
    }
}


@Preview
@Composable
fun ForgotPasswordScreenPreview() {
    val navController = rememberNavController()

    ForgotPasswordScreen(navController = navController)
}