package com.make_your_skill.ui.screens.matchSearch

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.ui.components.*
import com.make_your_skill.ui.theme.*

@Composable
fun MatchSearchScreen( navController: NavHostController) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val separation = 25.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(separation),
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
        CustomButton(
            onClick = { navController.navigate("ProfileMatchList") },
            text = "Match",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMatchSearchScreen() {
    val navController = rememberNavController()
    MatchSearchScreen(navController)
}

