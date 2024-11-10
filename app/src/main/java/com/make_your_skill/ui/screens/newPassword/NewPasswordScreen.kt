package com.make_your_skill.ui.screens.newPassword

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.make_your_skill.ui.components.buttons.CustomButton
import com.make_your_skill.ui.components.text.ScreenTitleText
import com.make_your_skill.ui.components.text.textFileds.TextInputLogin
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.screens.singIn.SingInViewModel
import com.make_your_skill.ui.theme.BackgroundColor2

@Composable
fun NewPasswordScreen(
    navController: NavHostController,
    singInViewModel: SingInViewModel
){
    val newPasswordViewModel: NewPasswordViewModel = viewModel()

    val oldPassword by newPasswordViewModel.oldPassword.collectAsState()
    val newPassword by newPasswordViewModel.newPassword.collectAsState()
    val reWriteNewPassword by newPasswordViewModel.reWriteNewPassword.collectAsState()
    val changePasswordInfo by newPasswordViewModel.changePasswordInfo.collectAsState()
    val changePasswordLoading by newPasswordViewModel.changePasswordLoading.collectAsState()
    val changePasswordError by newPasswordViewModel.changePasswordError.collectAsState()

    val userInfo by singInViewModel.signInInfo.collectAsState()

    val separation = 25.dp
    val BUTTON_TEXT = "CHANGE PASSWORD"
    val FIRST_TEXT = "Change"
    val SECOND_TEXT = "password..."
    val OLD_PASSWORD_LABEL = "Old password..."
    val NEW_PASSWORD_LABEL = "New password..."
    val RE_WIRTE_NEW_PASSWORD_LABEL = "Re write new password..."
    val LOADING = "Loading..."

    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusRequester3 = remember { FocusRequester() }

    LaunchedEffect(changePasswordInfo) {
        if (changePasswordError == null && changePasswordLoading.not() && changePasswordInfo != null) {
            newPasswordViewModel.resetVariables()
            navController.navigate(AppRoutes.SETTINGS_SCREEN)

            navController.navigate(AppRoutes.SETTINGS_SCREEN){
                popUpTo(0)
            }
        }
    }

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
                ScreenTitleText(FIRST_TEXT)
                ScreenTitleText(SECOND_TEXT)
                TextInputLogin(
                    label = OLD_PASSWORD_LABEL,
                    text = oldPassword,
                    onChange = newPasswordViewModel.onOldPasswordChange,
                    error = changePasswordError,
                    focusRequester = focusRequester1,
                    nextFocusRequester = focusRequester2,
                    isPassword = true,
                    color = BackgroundColor2
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextInputLogin(
                    label = NEW_PASSWORD_LABEL,
                    text = newPassword,
                    onChange = newPasswordViewModel.onNewPasswordChange,
                    error = changePasswordError,
                    focusRequester = focusRequester2,
                    nextFocusRequester = focusRequester3,
                    isPassword = true,
                    color = BackgroundColor2
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextInputLogin(
                    label = RE_WIRTE_NEW_PASSWORD_LABEL,
                    text = reWriteNewPassword,
                    onChange = newPasswordViewModel.onReWriteNewPasswordChange,
                    error = changePasswordError,
                    focusRequester = focusRequester3,
                    isPassword = true,
                    color = BackgroundColor2
                )
                if (changePasswordError != null){
                    Text(
                        text = changePasswordError.toString(),
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Row {
            CustomButton({newPasswordViewModel.onClick(
                singInViewModel.getToken(),
                userInfo!!.user.id
            )},if (changePasswordLoading) LOADING else BUTTON_TEXT)
        }
    }
}

