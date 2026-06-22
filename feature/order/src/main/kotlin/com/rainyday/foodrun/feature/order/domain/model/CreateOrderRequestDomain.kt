package com.rainyday.foodrun.feature.order.domain.model

data class CreateOrderRequestDomain(
    val restaurantId: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val items: List<OrderItemRequestDomain>
)