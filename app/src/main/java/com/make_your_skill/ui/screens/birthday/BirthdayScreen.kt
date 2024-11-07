package com.make_your_skill.ui.screens.birthday

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.make_your_skill.helpers.cookies.InMemoryCookieJar
import com.make_your_skill.helpers.functions.getNextDate
import com.make_your_skill.ui.components.CustomButton
import com.make_your_skill.ui.components.DatePickerDocked
import com.make_your_skill.ui.components.convertMillisToDate
import com.make_your_skill.ui.components.ScreenTitleText
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.screens.createNewAccount.CreateNewAcoountViewModel
import com.make_your_skill.ui.screens.singIn.SingInViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayScreen(
    navController: NavHostController,
    createNewAcoountViewModel: CreateNewAcoountViewModel,
    singInViewModel: SingInViewModel,
    cookieJar: InMemoryCookieJar
){
    val viewModel: BirthdayViewModel = viewModel()
    val dateOfBirth by createNewAcoountViewModel.dateOfBirth.collectAsState()
    val error by viewModel.error.collectAsState()
    val defaultDate by createNewAcoountViewModel.defaultDate.collectAsState()
    val registerError by createNewAcoountViewModel.registerError.collectAsState()
    val loading by createNewAcoountViewModel.loading.collectAsState()
    val registerInfo by createNewAcoountViewModel.registerInfo.collectAsState()

    val separation = 25.dp
    val BUTTON_TEXT = "CONTINUE 2/4"
    val FIRST_TEXT = "My"
    val SECOND_TEXT = "birthday is..."
    val REGISTERING_USER = "Registering user..."

    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        val currentDate = convertMillisToDate(it) // "11/05/2024"
        getNextDate(currentDate) // "11/06/2024"
    } ?: defaultDate

    LaunchedEffect(selectedDate) {
        if (selectedDate != "") {
            createNewAcoountViewModel.setDateOfBirth(selectedDate)
            createNewAcoountViewModel.setDefaultDate(selectedDate)
        }
    }

    LaunchedEffect(registerInfo) {
        if (registerError == null && loading.not() && registerInfo != null) {
            //Armamos para que si se registra exitosamente te logue automaticamente
            singInViewModel.setEmail(createNewAcoountViewModel.email.value)
            singInViewModel.setPassword(createNewAcoountViewModel.password.value)
            singInViewModel.onLogin(cookieJar)

            navController.navigate(AppRoutes.SKILLS_SCREEN)
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
                .fillMaxWidth() // Ocupa todo el ancho
                .fillMaxHeight(0.7f),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                ScreenTitleText(FIRST_TEXT)
                ScreenTitleText(SECOND_TEXT)

                DatePickerDocked(datePickerState,selectedDate)
            }
        }
        Row {
            CustomButton({viewModel.onClick(
                dateOfBirth,
                {createNewAcoountViewModel.register()}
            )},
                if (loading) REGISTERING_USER else BUTTON_TEXT
            )
        }
        if (error != null){
            Text(
                text = error.toString(),
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
        }
        if (registerError != null){
            Text(
                text = registerError.toString(),
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
