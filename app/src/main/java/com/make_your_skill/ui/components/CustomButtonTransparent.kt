package com.make_your_skill.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomButtonTransparent(text:String, onClick:() -> Unit, modifier: Modifier, color: Color){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = color
        ),
        border = BorderStroke(1.dp,color),
        modifier = Modifier
            .width(331.53.dp)
            .height(50.25.dp)


    ) {
        Text(text)
    }
}