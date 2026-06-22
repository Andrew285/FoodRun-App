package com.rainyday.foodrun.feature.cart.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("cart_items")
data class CartEntity(
    @PrimaryKey val menuItemId: String,
    val restaurantId: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val imageUrl: String? = null
)