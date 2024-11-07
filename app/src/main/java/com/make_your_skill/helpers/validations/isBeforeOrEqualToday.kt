package com.make_your_skill.helpers.validations

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun isDateBeforeOrEqualToday(dateString: String): Boolean {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val inputDate = dateFormat.parse(dateString) ?: return false
    val today = Calendar.getInstance().time

    return !inputDate.after(today) // Devuelve true si la fecha es antes o igual a hoy
}