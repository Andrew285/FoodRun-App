package com.rainyday.foodrun.feature.auth.domain.usecases

import com.rainyday.foodrun.feature.auth.domain.model.User
import com.rainyday.foodrun.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return repository.login(
            email = email,
            password = password
        )
    }
}