package com.make_your_skill.dataClasses.usersSkills.body

import com.make_your_skill.dataClasses.skills.skillDataClass
import com.make_your_skill.dataClasses.users.UserDataClass

data class GetUserSkillByUserId(
    val id:Int,
    val pricePerHour: Int,
    val user : UserDataClass,
    val skill : skillDataClass
)