package com.rainyday.foodrun.feature.cart.data.repository

import com.rainyday.foodrun.feature.cart.data.local.CartDao
import com.rainyday.foodrun.feature.cart.data.mappers.toDomain
import com.rainyday.foodrun.feature.cart.data.mappers.toEntity
import com.rainyday.foodrun.feature.cart.domain.model.CartItemDomain
import com.rainyday.foodrun.feature.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CartRepositoryImpl(
    private val dao: CartDao
): CartRepository {
    override fun getCartItems(): Flow<List<CartItemDomain>> =
        dao.getAllItems().map { list -> list.map { it.toDomain() } }

    override fun getCartItemCount(): Flow<Int> =
        dao.getTotalItemCount().map { it ?: 0 }

    override suspend fun addItem(item: CartItemDomain) = dao.insertItem(item.toEntity())

    override suspend fun removeItem(menuItemId: String) = dao.deleteItem(menuItemId)

    override suspend fun updateQuantity(menuItemId: String, quantity: Int) =
        dao.updateQuantity(menuItemId, quantity)

    override suspend fun clearCart() = dao.clearAll()
}