package com.rainyday.foodrun.feature.order.data.remote

data class OrderResponse(
    val id: String,
    val status: String,
    val totalPrice: Double,
    val address: String,
    val createdAt: String,
    val restaurantId: String,
    val items: List<OrderItemResponse>
)