package com.make_your_skill.ui.screens.firstName

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.make_your_skill.ui.components.buttons.CustomButton
import com.make_your_skill.ui.components.buttons.CustomTextField
import com.make_your_skill.ui.components.text.ScreenTitleText
import com.make_your_skill.ui.screens.createNewAccount.CreateNewAcoountViewModel

@Composable
fun FirstNameScreen(
    navController: NavHostController,
    createNewAcoountViewModel: CreateNewAcoountViewModel
) {
    val viewModel: FirstNameViewModel = viewModel()
    val firstname by createNewAcoountViewModel.firstname.collectAsState()
    val lastname by createNewAcoountViewModel.lastname.collectAsState()
    val error by viewModel.error.collectAsState()

    val separation = 25.dp
    val BUTTON_TEXT = "CONTINUE 1/4"
    val FIRST_TEXT = "My full"
    val SECOND_TEXT = "name is..."
    val FIRSTNAME_LABEL = "First Name..."
    val LASTNAME_LABEL = "Last Name..."

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(separation),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth() // Ocupa todo el ancho
                    .fillMaxHeight(0.7f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ScreenTitleText(FIRST_TEXT)
                ScreenTitleText(SECOND_TEXT)
                CustomTextField(
                    firstname,
                    createNewAcoountViewModel.onFirstNameChange,
                    FIRSTNAME_LABEL,
                    onSubmit = {},
                    false)

                CustomTextField(
                    lastname,
                    createNewAcoountViewModel.onLastNameChange,
                    LASTNAME_LABEL,
                    onSubmit = {},
                    false
                )
                if (error != null){
                    Text(
                        text = error.toString(),
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Row {
            CustomButton({viewModel.onClick(firstname,lastname,navController)},BUTTON_TEXT)
        }
    }
}