package com.rainyday.foodrun.feature.cart.data.mappers

import com.rainyday.foodrun.feature.cart.data.local.CartEntity
import com.rainyday.foodrun.feature.cart.domain.model.CartItemDomain


fun CartItemDomain.toEntity() = CartEntity(
    menuItemId = menuItemId,
    restaurantId = restaurantId,
    name = name,
    price = price,
    quantity = quantity,
    imageUrl = imageUrl
)

fun CartEntity.toDomain() = CartItemDomain(
    menuItemId = menuItemId,
    restaurantId = restaurantId,
    name = name,
    price = price,
    quantity = quantity,
    imageUrl = imageUrl
)
