package com.rainyday.foodrun.feature.order.data.remote

import com.rainyday.foodrun.feature.order.domain.model.OrderItemRequestDomain

data class OrderItemRequest(
    val menuItemId: String,
    val quantity: Int
)

fun OrderItemRequestDomain.toEntity(): OrderItemRequest = OrderItemRequest(
    menuItemId = menuItemId,
    quantity = quantity
)