package com.make_your_skill.dataClasses.auth.body

data class ChangePasswordBody(
    val oldPassword: String,
    val newPassword: String,
    val confirmNewPassword: String,
)