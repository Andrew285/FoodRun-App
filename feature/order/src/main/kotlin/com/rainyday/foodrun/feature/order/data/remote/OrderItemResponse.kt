package com.rainyday.foodrun.feature.order.data.remote

data class OrderItemResponse(
    val id: String,
    val menuItemId: String,
    val quantity: Int,
    val price: Double
)