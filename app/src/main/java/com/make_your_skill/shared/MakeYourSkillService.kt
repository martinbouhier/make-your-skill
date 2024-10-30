package com.make_your_skill.shared

import com.make_your_skill.model.MakeYourSkillApiModel
import com.make_your_skill.helpers.MakeYourSkillRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MakeYourSkillService {

    private val api = MakeYourSkillRetrofit().getRetrofit().create(ApiService::class.java)

    suspend fun getSkills(): List<MakeYourSkillApiModel>? = withContext(Dispatchers.IO) {
        val response = api.getSkills() // Suponiendo que `getSkills` es un método definido en `MakeYourSkillApi`
        if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}