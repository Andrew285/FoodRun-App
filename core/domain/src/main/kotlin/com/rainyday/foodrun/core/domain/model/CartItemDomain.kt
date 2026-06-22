package com.rainyday.foodrun.core.domain.model

data class CartItemDomain(
    val menuItemId: String,
    val restaurantId: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val imageUrl: String? = null
) {
    val totalPrice: Double get() = price * quantity
}