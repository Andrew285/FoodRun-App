package com.rainyday.foodrun.core.network.model

data class AuthResponseDto(
    val token: String,
    val user: UserDto
)