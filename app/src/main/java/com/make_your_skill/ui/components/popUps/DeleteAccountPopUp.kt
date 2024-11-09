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
fun DeleteAccountPopUp(
) {
    AlertDialog(
        title = {
            Text(text = "Rate")
        },
        text = {
            Column (
                modifier = Modifier
            ) {
            }
        },
        onDismissRequest = {
        },
        confirmButton = {
            TextButton(
                onClick = {
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                }
            ) {
                Text("Exit")
            }
        }
    )
}