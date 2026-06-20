package com.rainyday.foodrun.feature.home.domain.model

data class RestaurantDetailDomain(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val rating: Double,
    val address: String,
    val category: String,
    val isOpen: Boolean,
    val latitude: Double,
    val longitude: Double,
    val menuItems: List<MenuItemDomain>
)