package com.rainyday.foodrun.feature.auth.data.remote

data class AuthResponseDto(
    val token: String,
    val user: UserDto
)