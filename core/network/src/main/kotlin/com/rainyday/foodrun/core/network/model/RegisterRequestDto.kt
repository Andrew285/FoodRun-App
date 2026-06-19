package com.rainyday.foodrun.core.network.model

data class RegisterRequestDto(
    val email: String,
    val password: String,
    val name: String,
    val phone: String? = null
)