package com.make_your_skill.models.user

data class GetAllResponse (
    val results: List<User>
) {
    fun toModel() = results.map {
        User(
            id = it.id,
            firstname = it.firstname,
            lastname = it.lastname,
            email = it.email,
            isActive = it.isActive,
            rating = it.rating,
            dateOfBirth = it.dateOfBirth,
            createdAt = it.createdAt,
            updatedAt = it.updatedAt
        )
    }
}