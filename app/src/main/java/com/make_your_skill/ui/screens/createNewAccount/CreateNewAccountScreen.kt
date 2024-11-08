package com.make_your_skill.ui.screens.createNewAccount

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
import androidx.navigation.NavHostController
import com.make_your_skill.R
import com.make_your_skill.ui.components.buttons.CustomButton
import com.make_your_skill.ui.components.text.textFileds.TextInputLogin
import com.make_your_skill.ui.theme.BackgroundColor2


@Composable
fun CreateNewAccountScreen(
    navController: NavHostController,
    createNewAcoountViewModel: CreateNewAcoountViewModel
) {
    val isLoading by createNewAcoountViewModel.createNewAccountScreenLoading.collectAsState()
    val email by createNewAcoountViewModel.email.collectAsState()
    val password by createNewAcoountViewModel.password.collectAsState()
    val reWritePassword by createNewAcoountViewModel.reWritePassword.collectAsState()
    val createNewAccountScreenError by createNewAcoountViewModel.createNewAccountScreenError.collectAsState()

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
            TextInputLogin(label = "Email", text = email, onChange = createNewAcoountViewModel.onEmailChange, error = createNewAccountScreenError)
            Spacer(modifier = Modifier.height(10.dp))
            TextInputLogin(label = "Password", isPassword = true, text = password, onChange = createNewAcoountViewModel.onPasswordChange,error = createNewAccountScreenError)
            Spacer(modifier = Modifier.height(10.dp))
            TextInputLogin(label = "Re-Write password", isPassword = true, text = reWritePassword, onChange = createNewAcoountViewModel.onReWritePasswordChange,error = createNewAccountScreenError)
            Spacer(modifier = Modifier.height(10.dp))

            if (createNewAccountScreenError != null){
                Text(
                    text = createNewAccountScreenError.toString(),
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier.padding(bottom = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomButton(
                    onClick = {
                        createNewAcoountViewModel.onClick(navController)},
                    text = if (isLoading) "Loading..." else "CONTINUE",
                )
            }
        }
    }
}