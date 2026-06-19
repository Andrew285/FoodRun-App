package com.rainyday.foodrun.core.network.model

data class RestaurantDto(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val rating: Double,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val category: String,
    val isOpen: Boolean,
    val createdAt: String
)