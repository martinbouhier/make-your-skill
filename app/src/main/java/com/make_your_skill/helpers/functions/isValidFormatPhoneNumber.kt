package com.make_your_skill.helpers.functions

import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.NumberParseException

fun isArgentinianPhoneNumberValid(phoneNumber: String, countryCode: String = "AR"): Boolean {
    val phoneNumberUtil = PhoneNumberUtil.getInstance()
    return try {
        // Parsear el número de teléfono con el código de país
        val parsedNumber = phoneNumberUtil.parse(phoneNumber, countryCode)
        // Validar si el número es válido
        phoneNumberUtil.isValidNumber(parsedNumber)
    } catch (e: NumberParseException) {
        // Si no se puede parsear, el número no es válido
        false
    }
}