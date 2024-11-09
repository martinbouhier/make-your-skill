package com.make_your_skill.ui.components.popUps

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.make_your_skill.ui.theme.BackgroundColor2

@Composable
fun PopUpButton(
    onClick: () -> Unit,
    text: String
){
    TextButton(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .border(0.5.dp, Color.Black,RoundedCornerShape(8.dp))
            .background(BackgroundColor2)//TODO: color
            .wrapContentSize(Alignment.Center)
            .height(42.dp)
            .width(52.dp),
        onClick = onClick,
        contentPadding = PaddingValues(0.dp)

    ) {
        Text(
            text,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.wrapContentSize(Alignment.Center),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PopUpButtonPreview() {
    PopUpButton({}, "texto")
}