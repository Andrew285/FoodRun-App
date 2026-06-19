package com.rainyday.foodrun.core.network.model

data class RestaurantsResponseDto(
    val data: List<RestaurantDto>,
    val pagination: PaginationDto
)