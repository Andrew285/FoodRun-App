package com.rainyday.foodrun.feature.auth.data.repository

import com.rainyday.foodrun.core.datastore.TokenDataStore
import com.rainyday.foodrun.feature.auth.data.remote.AuthApi
import com.rainyday.foodrun.feature.auth.data.remote.LoginRequestDto
import com.rainyday.foodrun.feature.auth.data.remote.RegisterRequestDto
import com.rainyday.foodrun.feature.auth.domain.model.User
import com.rainyday.foodrun.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val tokenDataStore: TokenDataStore
): AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<User> =
        runCatching {
            val result = api.login(LoginRequestDto(email, password))
            tokenDataStore.saveToken(result.token)
            User(
                id = result.user.id,
                name = result.user.name,
                email = result.user.email
            )
        }

    override suspend fun register(name: String, email: String, password: String): Result<User> =
        runCatching {
            val response = api.register(RegisterRequestDto(name, email, password))
            tokenDataStore.saveToken(response.token)
            User(
                id = response.user.id,
                name = response.user.name,
                email = response.user.email
            )
        }
}