package com.make_your_skill.helpers.retrofit.users

import com.make_your_skill.models.user.User

interface UsersServiceInterface {
    suspend fun getAll(): List<User>?
    suspend fun findOne(): User?
    suspend fun findById(): User?
}