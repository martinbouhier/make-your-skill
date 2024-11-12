package com.make_your_skill.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.make_your_skill.dataClasses.skills.skillAddedDataClass
import com.make_your_skill.ui.theme.DarkPurple
import java.text.NumberFormat
import java.util.Locale

@Composable
fun SkillCard(
    skill: skillAddedDataClass,
    onSkillChange: (skillAddedDataClass) -> Unit)
{
    val GAP = 16.dp
    Column (modifier = Modifier.fillMaxWidth()) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
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
            Row (
                modifier = Modifier.padding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                Box {
                    Text(text = "$ " + skill.price?.let { getFormattedPrice(it) })
                }
                /* BOTON DE EDIT
                IconButton(onClick = onPriceEdit) {
                    Image(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit"
                    )
                }
                 */
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .height(1.dp) // Grosor de la línea
                .background(DarkPurple)
        )
    }
}

fun getFormattedPrice(precio: Float): String {
    val parteEntera = precio.toInt() // Convierte el float a entero (descarta los decimales)
    val format = NumberFormat.getNumberInstance(Locale.US)
    return format.format(parteEntera)
}

@Preview(showBackground = true)
@Composable
fun SkillCardPreview(){
    val skill = skillAddedDataClass(1, true, "Skill", 100000.0f)
    SkillCard(skill,{})

}