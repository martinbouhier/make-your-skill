package com.make_your_skill.helpers.functions

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun isOlder(dateOfBirth: String, minAge: Int): Boolean {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val birthDate = dateFormat.parse(dateOfBirth) ?: return false
    val today = Calendar.getInstance()

    // Calculamos la edad
    val birthCalendar = Calendar.getInstance().apply { time = birthDate }
    var age = today.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR)

    // Ajustamos si la fecha de nacimiento no ha pasado aún este año
    if (today.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
        age--
    }

    return age >= minAge
}