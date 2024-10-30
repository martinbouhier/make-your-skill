package com.make_your_skill.ui.screens.error



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.make_your_skill.ui.components.BackButton
import com.make_your_skill.ui.components.CustomButton
import com.make_your_skill.ui.components.ScreenTitleText
import com.make_your_skill.ui.navigation.AppRoutes
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.R
import com.make_your_skill.ui.components.ScreenContentText


@Composable
fun ErrorScreen(navController: NavHostController, errorMessage: String? = null, newAction: String? = null) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val separation = 16.dp
    val firstText = "Oops!"
    val secondText = errorMessage ?: "Error Message"
    val buttonText = newAction ?: "Action"


    val onClick = {
        navController.navigate(AppRoutes.FIRST_SCREEN)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(separation),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            BackButton(navController,Color.Gray)
        }
        Spacer(modifier = Modifier.height(screenHeight * 0.1f))
        Row (
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_purple),
                contentDescription = "App Logo",
                modifier = Modifier.size(128.dp)
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.2f))
        }
        Row () {
            ScreenTitleText(firstText)

        }
        Row () {
            ScreenContentText(secondText)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row {
                CustomButton(onClick,buttonText)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewErrorScreen() {
    val navController = rememberNavController()
    ErrorScreen(navController)
}
