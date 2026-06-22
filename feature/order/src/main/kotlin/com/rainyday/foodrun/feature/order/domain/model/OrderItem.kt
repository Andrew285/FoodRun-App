package com.rainyday.foodrun.feature.order.domain.model

data class OrderItem(
    val id: String,
    val menuItemId: String,
    val quantity: Int,
    val price: Double
)