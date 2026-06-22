package com.rainyday.foodrun.feature.order.domain.model

data class Order(
    val id: String,
    val status: OrderStatus,
    val totalPrice: Double,
    val address: String,
    val createdAt: String,
    val restaurantId: String,
    val items: List<OrderItem>
)