package com.rainyday.foodrun.feature.auth.data.remote

data class RegisterRequestDto(
    val email: String,
    val password: String,
    val name: String,
    val phone: String? = null
)