package com.make_your_skill.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.make_your_skill.ui.theme.DarkPurple

@Composable
fun ScreenContentText(text: String){
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = DarkPurple
    )
}