package com.make_your_skill.dataClasses.users

import com.make_your_skill.dataClasses.usersInterestedSkills.body.GetUserInterestedSkillsById
import com.make_your_skill.dataClasses.usersSkills.body.GetUserSkillByUserId

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
    val user_skills: List<GetUserSkillByUserId>? = emptyList(),
    val user_interested_skills: List<GetUserInterestedSkillsById>? = emptyList()
)
