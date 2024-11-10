package com.make_your_skill.dataClasses.users

import com.make_your_skill.dataClasses.skills.skillDataClass

data class UserDataClass(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val email: String,
    val phone: String,
    val isActive: Boolean,
    val votes: Int,
    val peopleVoted: Int,
    val dateOfBirth: String,
    val createdAt:String,
    val updatedAt:String,
    val user_skills: List<skillDataClass>? = emptyList(),
    val user_interested_skills: List<skillDataClass>? = emptyList()
)
