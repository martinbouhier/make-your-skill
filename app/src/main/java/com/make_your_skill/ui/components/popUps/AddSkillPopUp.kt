package com.make_your_skill.ui.components.popUps

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.make_your_skill.dataClasses.skills.skillDataClass
import com.make_your_skill.ui.components.buttons.CustomTextField
import com.make_your_skill.ui.components.dropDowns.skillsDropDown

@Composable
fun addSkillPopUp(
    onDismissRequest: () -> Unit,//Cerramos popup
    onConfirmation: () -> Unit,//Confirmamos el popup
    dialogTitle: String,//Titulo del popup
    onPriceAddChange: (String) -> Unit,//Cuando cambio el input del precio
    priceLabel: String,//Label del precio
    priceText: String,
    skillsList: List<skillDataClass>,
    onSkillAddChange: (Int) -> Unit
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
          Column (
              modifier = Modifier
          ) {
              skillsDropDown(skillsList, onSkillAddChange)
              CustomTextField(priceText,onPriceAddChange,priceLabel, onSubmit = {}, false)
          }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}