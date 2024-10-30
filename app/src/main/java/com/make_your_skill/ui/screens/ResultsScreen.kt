package com.make_your_skill.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.make_your_skill.ui.components.*
import com.make_your_skill.ui.theme.*
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun ResultsScreen(profiles: List<Profile>) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val separation = 10.dp

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