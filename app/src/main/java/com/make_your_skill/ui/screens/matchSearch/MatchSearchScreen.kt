package com.make_your_skill.ui.screens.matchSearch

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.ui.components.buttons.CustomButton
import com.make_your_skill.ui.components.buttons.CustomTextField
import com.make_your_skill.ui.components.sliders.RangeSlider
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.screens.singIn.SingInViewModel
import com.make_your_skill.ui.theme.*

@Composable
fun MatchSearchScreen(
    navController: NavHostController,
    singInViewModel: SingInViewModel,
) {
    val separation = 25.dp
    val TITLE_TEXT = "Learn"
    val LABEL = "Add skills..."
    val BUTTON_TEXT = "MATCH"

    val matchSearchViewModel: MatchSearchViewModel = viewModel()

    val listOfUserInterestedSkills by matchSearchViewModel.listOfUserInterestedSkills.collectAsState()
    val loadingInterests by matchSearchViewModel.loadingInterest.collectAsState()
    val errorInterests by matchSearchViewModel.errorInterest.collectAsState()

    val userInfo by singInViewModel.signInInfo.collectAsState()

    var text by remember { mutableStateOf("") }
    val skillsList = remember { mutableStateListOf<String>() } // Mantener la lista como mutableStateList

    val onTextChange: (String) -> Unit = { newText -> text = newText }

    LaunchedEffect(userInfo) {
        val token = userInfo!!.tokens.token
        matchSearchViewModel.getUserInterestedSkillByUserId(token,userInfo!!.user.id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(separation),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(separation * 2))
        Text(
            text = TITLE_TEXT,
            style = styleTitle,
            modifier = Modifier.padding(start = separation)
        )
        CustomTextField(
            text = text,
            onTextChange = onTextChange,
            label = LABEL,
            onSubmit = {
                if (text.isNotBlank()) { // Verifica que el texto no esté vacío
                    skillsList.add(text) // Agrega el texto a la lista
                    text = "" // Limpia el campo de texto
                }
            },
            isSubmitEnabled = true // Habilita el submit
        )
        Box(
            modifier = Modifier
                .height(180.dp) // Altura fija para la lista
                .padding(8.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 120.dp), // Ancho mínimo por elemento
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), // El scroll se gestiona en el propio LazyVerticalGrid
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(skillsList) { skill -> // Ahora usa skillsList directamente
                    Box(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = Color(0xFF4E40EA).copy(alpha = 0.8f),
                                shape = CircleShape
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = skill,
                            color = Color(0xFF4E40EA).copy(alpha = 0.8f)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(separation))

        RangeSlider()

        Spacer(modifier = Modifier.height(separation))
        CustomButton(
            onClick = { navController.navigate(AppRoutes.MATCH_SEARCH_SCREEN) },
            text = BUTTON_TEXT,
        )
        Spacer(modifier = Modifier.height(100.dp))
    }
}
