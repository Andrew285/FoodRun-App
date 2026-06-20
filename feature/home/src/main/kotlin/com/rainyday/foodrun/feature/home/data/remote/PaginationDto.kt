package com.rainyday.foodrun.feature.home.data.remote

data class PaginationDto(
    val page: Int,
    val limit: Int,
    val total: Int,
    val totalPages: Int
)