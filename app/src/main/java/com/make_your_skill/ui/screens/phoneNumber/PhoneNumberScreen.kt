package com.make_your_skill.ui.screens.phoneNumber

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.ui.components.buttons.CustomButton
import com.make_your_skill.ui.components.buttons.CustomTextField
import com.make_your_skill.ui.components.text.ScreenTitleText
import com.make_your_skill.ui.screens.createNewAccount.CreateNewAcoountViewModel
import com.make_your_skill.ui.screens.firstName.FirstNameViewModel

@Composable
fun PhoneNumberScreen(
    navController: NavHostController,
    createNewAcoountViewModel: CreateNewAcoountViewModel
) {
    val viewModel: PhoneNumberViewModel = viewModel()
    val phoneNumber by createNewAcoountViewModel.phoneNumber.collectAsState()
    val error by viewModel.error.collectAsState()

    val separation = 25.dp
    val BUTTON_TEXT = "CONTINUE"
    val FIRST_TEXT = "My cell phone "
    val SECOND_TEXT = "number is..."
    val PHONE_NUMBER_LABEL = "Cell phone..."

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
                    phoneNumber,
                    createNewAcoountViewModel.onPhoneNumberChange,
                    PHONE_NUMBER_LABEL,
                    onSubmit = {},
                    false)


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
            CustomButton({viewModel.onClick(phoneNumber,navController)},BUTTON_TEXT)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPhoneNumberScreen() {
    val navController = rememberNavController()
    val viewModel: CreateNewAcoountViewModel = viewModel()
    PhoneNumberScreen(navController, viewModel)
}