package com.rainyday.foodrun.feature.home.domain.model

data class MenuItemDomain(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
    val isAvailable: Boolean
)