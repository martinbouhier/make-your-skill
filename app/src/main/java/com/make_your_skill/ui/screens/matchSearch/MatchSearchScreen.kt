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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.ui.components.*
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.theme.*

var skillsList = mutableListOf<String>()
val sampleList= listOf(
    "Kotlin",
    "Compose",
    "Jetpack",
    "Java",
    "Android",
    "Flutter",
    "Compose",
    "Jetpack",
    "Java",
    "Android",
    "Flutter",
    "Compose",
    "Jetpack",
    "Java",
    "Android",
    "Flutter",
    "iOS")

@Composable
fun MatchSearchScreen( navController: NavHostController) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val separation = 25.dp
    val TITLE_TEXT = "Learn"
    val LABEL = "Bask..."
    val BUTTON_TEXT = "MATCH"

    var text by remember { mutableStateOf("") }
    val onTextChange: (String) -> Unit = { newText ->
        text = newText
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(separation),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Spacer(modifier = Modifier.height(separation * 2))
        Text(
            text = TITLE_TEXT,
            style = styleTitle,
            modifier = Modifier.padding(start = separation)
        )
        CustomTextField(text,onTextChange,LABEL)
        Box( // Utilizamos una Box para contener la Matriz y permitir que haya un scroll.
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
                items(sampleList) { skill ->
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
            onClick = { navController.navigate("ProfileMatchList") },
            text = BUTTON_TEXT,
        )
        Spacer(modifier = Modifier.height(100.dp))
    }
}

private fun addSkill(skill: String) {
    skillsList.add(skill)
}

@Preview(showBackground = true)
@Composable
fun PreviewMatchSearchScreen() {
    val navController = rememberNavController()
    MatchSearchScreen(navController)
}

