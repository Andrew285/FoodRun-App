package com.rainyday.foodrun.feature.restaurant.domain.model

import com.rainyday.foodrun.core.domain.model.MenuItemDomain

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