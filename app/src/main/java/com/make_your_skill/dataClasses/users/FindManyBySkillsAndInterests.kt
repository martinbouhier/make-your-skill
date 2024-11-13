package com.make_your_skill.dataClasses.users

data class FindManyBySkillsAndInterests(
    val skillsIds: String,
    val interestsIds: String?
)
