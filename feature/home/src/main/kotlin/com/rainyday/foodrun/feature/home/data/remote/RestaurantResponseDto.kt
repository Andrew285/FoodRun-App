package com.rainyday.foodrun.feature.home.data.remote

data class RestaurantsResponseDto(
    val data: List<RestaurantDto>,
    val pagination: PaginationDto
)