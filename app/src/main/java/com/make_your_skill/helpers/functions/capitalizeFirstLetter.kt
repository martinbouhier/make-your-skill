package com.make_your_skill.helpers.functions

fun capitalizeFirstLetter(input: String): String {
    return input.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}
