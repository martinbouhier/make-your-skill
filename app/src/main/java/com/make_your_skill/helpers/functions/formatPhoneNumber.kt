package com.make_your_skill.helpers.functions

fun formatPhoneNumberToArgentinianFormat(phoneNumber: String): String {
    // Eliminar caracteres no numéricos
    val cleanedNumber = phoneNumber.replace(Regex("[^\\d]"), "")

    if (cleanedNumber.length < 10) {
        return phoneNumber // Si es demasiado corto, se devuelve tal cual
    }

    return when {
        // Caso celular con prefijo internacional (+54 9)
        cleanedNumber.startsWith("549") && cleanedNumber.length >= 13 -> {
            "+54 9 ${cleanedNumber.substring(3, 5)} ${cleanedNumber.substring(5, 9)} ${cleanedNumber.substring(9)}"
        }
        // Caso celular sin prefijo internacional (código de área con 9)
        cleanedNumber.startsWith("9") && cleanedNumber.length == 11 -> {
            "+54 9 ${cleanedNumber.substring(1, 3)} ${cleanedNumber.substring(3, 7)} ${cleanedNumber.substring(7)}"
        }
        // Caso teléfono fijo con prefijo internacional (+54)
        cleanedNumber.startsWith("54") && cleanedNumber.length >= 11 -> {
            "+54 ${cleanedNumber.substring(2, 4)} ${cleanedNumber.substring(4, 8)} ${cleanedNumber.substring(8)}"
        }
        // Caso teléfono fijo sin prefijo internacional (código de área)
        cleanedNumber.length == 10 -> {
            "+54 ${cleanedNumber.substring(0, 2)} ${cleanedNumber.substring(2, 6)} ${cleanedNumber.substring(6)}"
        }
        else -> phoneNumber // Devuelve el número original si no encaja en los casos anteriores
    }
}
