package com.make_your_skill.helpers.functions

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun getNextDate(dateString: String): String? {
    val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    val date = dateFormat.parse(dateString) ?: return null

    // Incrementamos solo un día
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar.add(Calendar.DAY_OF_YEAR, 1) // Aumenta un día

    return dateFormat.format(calendar.time) // Devolvemos la fecha incrementada en formato String
}