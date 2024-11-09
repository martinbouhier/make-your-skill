package com.make_your_skill.ui.screens.matchSearch

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.ui.components.buttons.CustomButton
import com.make_your_skill.ui.components.buttons.CustomTextField
import com.make_your_skill.ui.components.sliders.RangeSlider
import com.make_your_skill.ui.components.text.CircularText
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.theme.*

@Composable
fun MatchSearchScreen(navController: NavHostController) {
    val separation = 25.dp
    val TITLE_TEXT = "Learn"
    val LABEL = "Add skills..."
    val BUTTON_TEXT = "MATCH"

    var text by remember { mutableStateOf("") }
    val skillsList = remember { mutableStateListOf<String>() } // Mantener la lista como mutableStateList

    val onTextChange: (String) -> Unit = { newText -> text = newText }

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
                if (text.isNotBlank()) {
                    skillsList.add(text)
                    text = ""
                }
            },
            isSubmitEnabled = true // Habilita el submit
        )
        Box(
            modifier = Modifier
                .height(180.dp)
                .padding(8.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 120.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(skillsList) { skill ->
                    CircularText(skill)
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

@Preview(showBackground = true)
@Composable
fun PreviewMatchSearchScreen() {
    val navController = rememberNavController()
    MatchSearchScreen(navController)
}

