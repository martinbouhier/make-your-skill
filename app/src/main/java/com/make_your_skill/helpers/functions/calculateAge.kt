package com.make_your_skill.helpers.functions

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun calculateAge(dateString: String): Int {
    // Formato de la fecha de entrada
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    // Convertir el string a una fecha
    val birthDate = LocalDate.parse(dateString, formatter)

    // Fecha actual
    val currentDate = LocalDate.now()

    // Calcular la diferencia en años
    return Period.between(birthDate, currentDate).years
}