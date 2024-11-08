package com.make_your_skill.ui.screens.results

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.make_your_skill.ui.theme.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.dataClasses.Profile
import com.make_your_skill.ui.components.cards.ProfileCard
import java.sql.Date

@Composable
fun ResultsScreen(navController: NavHostController) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val separation = 10.dp

    val profiles = listOf(
        Profile("John", "Doe", "1.34",
            Date(1990, 1, 1) // TODO: Cambiar Package
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(separation * 2),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(screenHeight * 0.1f))
        Text(
            text = "Matches",
            style = styleSubtitle,
            modifier = Modifier
                .padding(separation)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(separation),
            verticalArrangement = Arrangement.spacedBy(separation) // Espacio entre cards
        ) {
            items(profiles) { profile ->
                ProfileCard(profile)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewResultsScreen() {
    val navController = rememberNavController()
    ResultsScreen(navController)
}
