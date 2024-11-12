package com.make_your_skill.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.make_your_skill.dataClasses.skills.skillAddedDataClass
import com.make_your_skill.ui.theme.DarkPurple

@Composable
fun SkillCard(
    skill: skillAddedDataClass,
    onSkillChange: (skillAddedDataClass) -> Unit,
    onPriceEdit: () -> Unit){
    Column {
        Row (
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
            ) {
                Checkbox(
                    checked = skill.selected,
                    onCheckedChange = { isChecked ->
                        // Actualizar el skill con el nuevo valor de selected
                        onSkillChange(skill.copy(selected = isChecked))
                    }
                )
                Text(
                    skill.skill
                )


            }
            Box {
                Text(text = (skill.price).toString() + "$")
            }
            IconButton(onClick = onPriceEdit) {
                Image(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit"
                )
            }

        }
        Box (
            modifier = Modifier
                .fillMaxWidth(0.7f) // Asegúrate de que la línea ocupa el mismo ancho que la Row
                .height(1.dp) // Grosor de la línea
                .background(DarkPurple)
        ){}
    }
}
@Preview(showBackground = true)
@Composable
fun SkillCardPreview(){
    val skill = skillAddedDataClass(1, true, "Skill", 10.0f)
    SkillCard(skill,{},{})

}