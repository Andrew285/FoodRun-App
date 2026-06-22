package com.rainyday.foodrun.feature.cart.data.repository

import com.rainyday.foodrun.core.domain.model.CartItemDomain
import com.rainyday.foodrun.core.domain.repository.CartRepository
import com.rainyday.foodrun.feature.cart.data.local.CartDao
import com.rainyday.foodrun.feature.cart.data.mappers.toDomain
import com.rainyday.foodrun.feature.cart.data.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
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