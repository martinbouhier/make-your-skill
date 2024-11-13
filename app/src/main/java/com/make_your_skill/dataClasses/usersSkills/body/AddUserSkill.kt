package com.make_your_skill.dataClasses.usersSkills.body

data class AddUserSkill(//TODO: Cambiar el nombre de esta clase
    val userId: Int,
    val skillId: Int,
    val pricePerHour: Float
)
