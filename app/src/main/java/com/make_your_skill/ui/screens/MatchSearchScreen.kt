package com.make_your_skill.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.make_your_skill.ui.components.*
import com.make_your_skill.ui.theme.*

@Composable
fun MatchSearchScreen( navController: NavHostController) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val separation = 25.dp

    Column(
        modifier = Modifier.fillMaxSize().padding(separation),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Spacer(modifier = Modifier.height(screenHeight * 0.1f))

        Text(
            text = "Learn",
            style = styleTitle,
            modifier = Modifier.padding(start = separation)
        )
        Spacer(modifier = Modifier.height(400.dp))

        RangeSlider()

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.padding(bottom = 150.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(
                onClick = { navController.navigate("ProfileMatchList") },
                text = "Match",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}