package com.make_your_skill.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.make_your_skill.R
import com.make_your_skill.ui.components.CustomButton

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(screenHeight * 0.1f))  // Add space to position the image
        Image(
            painter = painterResource(id = R.drawable.logo_purple),
            contentDescription = "App Logo",
            modifier = Modifier.size(128.dp)  // Adjust size as needed
        )
        Spacer(modifier = Modifier.height(160.dp))  // Add some space between elements
        CustomButton(onClick = { /* TODO: Add action */ }, text = "MATCH")
        Text(text = "OR")
        Spacer(modifier = Modifier.height(16.dp))  // Add some space between elements
        CustomButton(onClick = { /* TODO: Add action */ }, text = "SEARCH FOR PAID CLASSES")
    }
}