package com.make_your_skill.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.make_your_skill.dataClasses.usersInterestedSkills.body.InterestAddedDataClass
import com.make_your_skill.ui.theme.DarkPurple

@Composable
fun interest(
    skill: InterestAddedDataClass,
    onSkillChange: (InterestAddedDataClass) -> Unit)
{
    Column (modifier = Modifier.fillMaxWidth()) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = skill.selected,
                onCheckedChange = { isChecked ->
                    // Actualizar el skill con el nuevo valor de selected
                    onSkillChange(skill.copy(selected = isChecked))
                }
            )
            Text(skill.skill)
        }
        HorizontalDivider(
            modifier = Modifier
                .height(1.dp) // Grosor de la línea
                .background(DarkPurple)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun interestPreview() {
    val sampleSkill = InterestAddedDataClass(1, true, "Kotlin")
    interest(skill = sampleSkill, onSkillChange = {})
}