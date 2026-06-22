package com.rainyday.foodrun.feature.order.domain.model

data class OrderItemRequestDomain(
    val menuItemId: String,
    val quantity: Int
)