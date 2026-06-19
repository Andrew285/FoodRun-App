package com.rainyday.foodrun.feature.auth.domain.repository

import com.rainyday.foodrun.feature.auth.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(name: String, email: String, password: String): Result<User>
}