package com.make_your_skill.ui.screens.firstName

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.make_your_skill.ui.components.BackButton
import com.make_your_skill.ui.components.CustomButton
import com.make_your_skill.ui.components.CustomTextField
import com.make_your_skill.ui.components.ScreenTitleText
import com.make_your_skill.ui.navigation.AppRoutes

@Composable
fun FirstNameScreen(navController: NavHostController) {
    var text by remember { mutableStateOf("") }
    val separation = 25.dp
    val BUTTON_TEXT = "CONTINUE 1/4"
    val FIRST_TEXT = "My first"
    val SECOND_TEXT = "name is..."
    val LABEL = "Name..."

    val onTextChange: (String) -> Unit = { newText ->
        text = newText
    }

    val onClick = {
        if (text != ""){
            navController.navigate(AppRoutes.BIRTHDAY_SCREEN)
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
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            BackButton(navController,Color.Gray)
        }
        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ScreenTitleText(FIRST_TEXT)
                ScreenTitleText(SECOND_TEXT)
                CustomTextField(text,onTextChange,LABEL)
            }
        }
        Row {
            CustomButton(onClick,BUTTON_TEXT)
        }
    }
}