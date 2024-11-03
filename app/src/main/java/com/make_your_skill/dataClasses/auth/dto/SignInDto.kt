package com.make_your_skill.dataClasses.auth.dto

import com.make_your_skill.dataClasses.TokenDataClass
import com.make_your_skill.dataClasses.users.UserDataClass

data class SignInDto(
    val user: UserDataClass,
    val tokens: TokenDataClass
)
