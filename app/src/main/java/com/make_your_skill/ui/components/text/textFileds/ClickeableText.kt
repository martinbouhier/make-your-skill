package com.make_your_skill.ui.components.text.textFileds

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun ClickableText(text : String, onClick: () -> Unit, color : Color) {
    Text(
        text = text,
        color = color,
        modifier = Modifier
            .clickable { onClick() },
        fontSize = 18.sp,

    )
}