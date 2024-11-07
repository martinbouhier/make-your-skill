package com.make_your_skill.dataClasses.usersInterestedSkills.body

import com.make_your_skill.dataClasses.skills.skillDataClass
import com.make_your_skill.dataClasses.users.UserDataClass

data class GetUserInterestedSkillsById(
    val id:Int,
    val user : UserDataClass,
    val skill : skillDataClass
)