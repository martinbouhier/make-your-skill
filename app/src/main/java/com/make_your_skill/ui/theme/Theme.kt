// Theme.kt
package com.make_your_skill.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Purple,
    onPrimary = White,
    secondary = DarkBlue,
    onSecondary = White,
)

@Composable
fun MakeYourSkillTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}