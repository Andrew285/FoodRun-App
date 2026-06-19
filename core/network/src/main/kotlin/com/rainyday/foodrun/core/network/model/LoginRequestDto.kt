package com.rainyday.foodrun.core.network.model

data class LoginRequestDto(
    val email: String,
    val password: String
)