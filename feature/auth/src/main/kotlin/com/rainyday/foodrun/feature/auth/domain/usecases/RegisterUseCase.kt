package com.rainyday.foodrun.feature.auth.domain.usecases

import com.rainyday.foodrun.feature.auth.domain.model.User
import com.rainyday.foodrun.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(name: String, email: String, password: String): Result<User> =
        repository.register(name, email, password)
}