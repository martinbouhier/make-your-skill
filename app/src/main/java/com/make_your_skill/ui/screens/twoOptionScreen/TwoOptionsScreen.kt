package com.make_your_skill.ui.screens.twoOptionScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.R
import com.make_your_skill.ui.components.buttons.CustomButton
import com.make_your_skill.ui.theme.styleNormalText


@Composable
fun TwoOptionsScreen(
    navController: NavHostController,
    firstButtonText: String,
    secondButtonText: String,
    firstButtonAction: () -> Unit,
    secondButtonAction: () -> Unit,
    description: String = "") {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val separation = 16.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(separation),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(screenHeight * 0.15f))
        Image(
            painter = painterResource(id = R.drawable.logo_purple),
            contentDescription = "App Logo",
            modifier = Modifier.size(78.dp)
        )
        if(description != ""){
            Spacer(modifier = Modifier.height(screenHeight * 0.08f))
            Text(
                text = description,
                style = styleNormalText
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.04f))
        }else{
            Spacer(modifier = Modifier.height(screenHeight * 0.15f))
        }
        CustomButton(onClick = firstButtonAction,
            text = firstButtonText)
        Spacer(modifier = Modifier.height(separation*3))
        Text(
            text = "OR",
            style = styleNormalText
        )
        Spacer(modifier = Modifier.height(separation*3))
        CustomButton(onClick = secondButtonAction,
            text = secondButtonText)

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTwoOptionsScreen() {
    val navController = rememberNavController()
    TwoOptionsScreen(navController, "MATCH", "SEARCH FOR PAID CLASSES", { }, { }, "a")
}

