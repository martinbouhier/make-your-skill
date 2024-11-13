package com.make_your_skill.helpers.functions

fun calculateRate(vote: Int, peopleVoted: Int): Float {
    return if (peopleVoted == 0) {
        0f // Retorna 0 si no hay votantes
    } else {
        String.format("%.1f", vote.toFloat() / peopleVoted).toFloat()// Calcula el promedio como Float
    }
}