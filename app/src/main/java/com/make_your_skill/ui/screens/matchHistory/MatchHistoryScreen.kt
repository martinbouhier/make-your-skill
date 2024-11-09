package com.make_your_skill.ui.screens.matchHistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.dataClasses.users.UserDataClass
import com.make_your_skill.ui.components.ItemMatchHistory
import com.make_your_skill.ui.theme.BackgroundColor2

@Composable
fun MatchHistory(navController: NavHostController) {
    val paddingValues = 16.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val listaPersonas = listOf(
        UserDataClass(1, "John","Doe","john@gmail.com", phone = "1234 5678",true, 5,1,"01/01/2000","01/01/2024","01/01/2024"),
        UserDataClass(1, "John","Doe","john@gmail.com", phone = "1234 5678",true, 5,1,"01/01/2000","01/01/2024","01/01/2024"),
        UserDataClass(1, "John","Doe","john@gmail.com", phone = "1234 5678",true, 5,1,"01/01/2000","01/01/2024","01/01/2024")
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(screenHeight * 0.1f))
        Text(
            text = "Match history",
            fontSize = 20.sp,
            color = BackgroundColor2,
            modifier = Modifier.padding(bottom = paddingValues)
        )
        LazyColumn {
            items(listaPersonas) { persona ->
                ItemMatchHistory(persona)
                HorizontalDivider(
                    color = Color.LightGray,
                    thickness = 2.dp,
                    modifier = Modifier.padding(horizontal = 1.dp)
                )
                Spacer(modifier = Modifier.height(paddingValues))
            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun PantallaHistorialPreview() {
   val navController = rememberNavController()
    MatchHistory(navController)
}
//