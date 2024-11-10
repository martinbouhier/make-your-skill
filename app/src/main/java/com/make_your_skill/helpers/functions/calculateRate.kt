package com.make_your_skill.helpers.functions

fun calculateRate(vote: Int, peopleVoted: Int): Float {
    return if (peopleVoted == 0) {
        0f // Retorna 0 si no hay votantes
    } else {
        vote.toFloat() / peopleVoted // Calcula el promedio como Float
    }
}