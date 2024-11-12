package com.make_your_skill.ui.screens.createNewAccount

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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

    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusRequester3 = remember { FocusRequester() }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor2)
            .padding(16.dp)
        ,
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
        }

        // INPUTS
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            TextInputLogin(
                label = "Email",
                text = email,
                onChange = createNewAcoountViewModel.onEmailChange,
                error = createNewAccountScreenError,
                focusRequester = focusRequester1,
                nextFocusRequester = focusRequester2,
                )

            Spacer(modifier = Modifier.height(10.dp))

            TextInputLogin(
                label = "Password",
                isPassword = true,
                text = password,
                onChange = createNewAcoountViewModel.onPasswordChange,
                error = createNewAccountScreenError,
                focusRequester = focusRequester2,
                nextFocusRequester = focusRequester3
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextInputLogin(
                label = "Re-Write password",
                isPassword = true,
                text = reWritePassword,
                onChange = createNewAcoountViewModel.onReWritePasswordChange,
                error = createNewAccountScreenError,
                focusRequester = focusRequester3,
                onImeAction = {createNewAcoountViewModel.onClick(navController)}
            )
            Spacer(modifier = Modifier.height(75.dp))

            if (createNewAccountScreenError != null){
                Text(
                    text = createNewAccountScreenError.toString(),
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // BOTON DE CONTINUE
        Column(
            modifier = Modifier.padding(bottom = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(
                onClick = {
                    createNewAcoountViewModel.onClick(navController)},
                text = if (isLoading) "Loading..." else "CREATE NEW ACCOUNT",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateNewAccountScreenPreview() {
    CreateNewAccountScreen(rememberNavController(), hiltViewModel())
}

