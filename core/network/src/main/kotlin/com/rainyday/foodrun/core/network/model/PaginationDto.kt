package com.rainyday.foodrun.core.network.model

data class PaginationDto(
    val page: Int,
    val limit: Int,
    val total: Int,
    val totalPages: Int
)