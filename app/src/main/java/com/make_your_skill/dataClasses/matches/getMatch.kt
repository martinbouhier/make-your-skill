package com.make_your_skill.dataClasses.matches

import com.make_your_skill.dataClasses.skills.skillDataClass
import com.make_your_skill.dataClasses.users.UserDataClass

data class getMatch(
    val id:Int,
    val userA : UserDataClass,
    val userB : UserDataClass,
    val skillA : skillDataClass,
    val skillB : skillDataClass
)
