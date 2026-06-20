package com.rainyday.foodrun.core.data.remote

data class MenuItemDto(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
    val isAvailable: Boolean,
    val restaurantId: String
)