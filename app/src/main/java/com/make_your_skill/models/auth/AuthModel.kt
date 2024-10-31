package com.make_your_skill.models.auth

import com.make_your_skill.models.token.Token
import com.make_your_skill.models.user.User

data class Auth(
    val user: User,
    val tokens: Token
)