package com.make_your_skill.ui.screens.birthday

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
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
import com.make_your_skill.ui.components.DatePickerDocked
import com.make_your_skill.ui.components.convertMillisToDate
import com.make_your_skill.ui.components.customText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayScreen(navController: NavHostController){
    val separation = 25.dp
    val BUTTON_TEXT = "CONTINUE 2/4"
    val FIRST_TEXT = "My"
    val SECOND_TEXT = "birthday is..."

    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""


    val onClick = {}

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
            BackButton(navController, Color.Gray)
        }
        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                customText(FIRST_TEXT)
                customText(SECOND_TEXT)
                DatePickerDocked(datePickerState,selectedDate)
            }
        }
        Row {
            CustomButton(onClick,BUTTON_TEXT)
        }
    }
}