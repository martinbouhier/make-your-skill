package com.make_your_skill.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.make_your_skill.ui.screens.skill.skillDataClass

@Composable
fun addInterestsPopUp(
    onDismissRequest: () -> Unit,//Cerramos popup
    onConfirmation: () -> Unit,//Confirmamos el popup
    dialogTitle: String,//Titulo del popup
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