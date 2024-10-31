package com.make_your_skill.models.user

data class FindByIdResponse (
    val result: User
) {
    fun toModel() = User(
            id = result.id,
            firstname = result.firstname,
            lastname = result.lastname,
            email = result.email,
            isActive = result.isActive,
            rating = result.rating,
            dateOfBirth = result.dateOfBirth,
            createdAt = result.createdAt,
            updatedAt = result.updatedAt
        )
}