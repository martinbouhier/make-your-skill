package com.make_your_skill

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.viewModel.MakeYourSkillViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = MakeYourSkillViewModel()
            val navController = rememberNavController()
            AppNavigation(navController)
        }
    }
}

