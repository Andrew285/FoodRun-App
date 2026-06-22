package com.rainyday.foodrun.feature.order.data.remote

import com.rainyday.foodrun.feature.order.domain.model.CreateOrderRequestDomain

data class CreateOrderRequest(
    val restaurantId: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val items: List<OrderItemRequest>
)

fun CreateOrderRequestDomain.toEntity(): CreateOrderRequest = CreateOrderRequest(
    restaurantId = restaurantId,
    address = address,
    latitude = latitude,
    longitude = longitude,
    items = items.map { it.toEntity() }
)