package com.make_your_skill.helpers.functions

fun isArgentinianPhoneNumberValid(phoneNumber: String): Boolean {
    // Eliminar caracteres no numéricos
    val cleanedNumber = phoneNumber.replace(Regex("[^\\d]"), "")

    // Expresiones regulares para validar los números en formato de Argentina
    val regexMobileInternational = Regex("^549\\d{10}\$") // Celular internacional (+54 9 XXXXX XXXXX)
    val regexMobileNational = Regex("^9\\d{10}\$")        // Celular nacional (9XXXXXXXXXX)
    val regexFixedInternational = Regex("^54\\d{10}\$")   // Fijo internacional (+54 XXXXX XXXXX)
    val regexFixedNational = Regex("^\\d{10}\$")          // Fijo nacional (XXXXXXXXXX)

    // Verificar si coincide con alguno de los formatos
    return regexMobileInternational.matches(cleanedNumber) ||
            regexMobileNational.matches(cleanedNumber) ||
            regexFixedInternational.matches(cleanedNumber) ||
            regexFixedNational.matches(cleanedNumber)
}