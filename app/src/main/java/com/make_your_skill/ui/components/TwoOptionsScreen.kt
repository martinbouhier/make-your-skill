package com.make_your_skill.ui.components

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
import com.make_your_skill.ui.theme.styleNormalText


@Composable
fun TwoOptionsScreen(navController: NavHostController, firstButtonText: String, secondButtonText: String, firstButtonAction: () -> Unit, secondButtonAction: () -> Unit, description: String) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val separation = 16.dp

    Column(
        modifier = Modifier.fillMaxSize().padding(separation),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(screenHeight * 0.1f))
        Image(
            painter = painterResource(id = R.drawable.logo_purple),
            contentDescription = "App Logo",
            modifier = Modifier.size(128.dp)
        )
        Spacer(modifier = Modifier.height(160.dp))
        Text(
            text = description,
            style = styleNormalText
        )
        Spacer(modifier = Modifier.height(160.dp))
        CustomButton(onClick = firstButtonAction,
            text = firstButtonText)
        Spacer(modifier = Modifier.height(separation))
        Text(
            text = "OR",
            style = styleNormalText
        )
        Spacer(modifier = Modifier.height(separation))
        CustomButton(onClick = secondButtonAction,
            text = secondButtonText)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTwoOptionsScreen() {
    val navController = rememberNavController()
    TwoOptionsScreen(navController, "MATCH", "SEARCH FOR PAID CLASSES", { }, { }, "")
}

