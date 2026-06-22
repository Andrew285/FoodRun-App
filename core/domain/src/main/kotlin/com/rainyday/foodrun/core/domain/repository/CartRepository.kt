package com.rainyday.foodrun.core.domain.repository

import com.rainyday.foodrun.core.domain.model.CartItemDomain
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartItems(): Flow<List<CartItemDomain>>
    fun getCartItemCount(): Flow<Int>
    suspend fun addItem(item: CartItemDomain)
    suspend fun removeItem(menuItemId: String)
    suspend fun updateQuantity(menuItemId: String, quantity: Int)
    suspend fun clearCart()
}