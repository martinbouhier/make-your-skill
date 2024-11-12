package com.make_your_skill.dataClasses.matches

data class CreateMatchDto(
    val userAId: Int,
    val userBId: Int,
    val skillAId: Int?,
    val skillBId: Int
)
