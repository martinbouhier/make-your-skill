package com.make_your_skill.dataClasses.usersSkills.body

data class AddUserSkill(
    val userId: Int,
    val skillId: Int,
    val pricePerHour: Float
)
