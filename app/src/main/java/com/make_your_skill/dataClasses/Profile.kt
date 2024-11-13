package com.make_your_skill.dataClasses

import java.util.Calendar
import java.util.Date
import androidx.compose.runtime.Immutable

@Immutable
data class Profile(
    val name: String,
    val info: String,
    val rate: String,
    val birthdate : Date,
) {
    fun getAge(): Int {
        val calendar = Calendar.getInstance()
        calendar.time = birthdate

        val birthYear = calendar.get(Calendar.YEAR)
        val birthMonth = calendar.get(Calendar.MONTH)
        val birthDay = calendar.get(Calendar.DAY_OF_MONTH)

        val currentCalendar = Calendar.getInstance()
        val currentYear = currentCalendar.get(Calendar.YEAR)
        val currentMonth = currentCalendar.get(Calendar.MONTH)
        val currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH)

        var age = currentYear - birthYear

        // En caso de que todavía no cumplió años este año
        if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
            age--
        }

        return age
    }
}
