package com.rainyday.foodrun.feature.order.domain.usecases

import com.rainyday.foodrun.core.domain.model.CartItemDomain
import com.rainyday.foodrun.feature.order.domain.model.Order
import com.rainyday.foodrun.feature.order.domain.repository.OrderRepository
import com.rainyday.foodrun.feature.order.domain.model.CreateOrderRequestDomain
import com.rainyday.foodrun.feature.order.domain.model.OrderItemRequestDomain
import javax.inject.Inject

class CreateOrderUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(
        restaurantId: String,
        address: String,
        latitude: Double,
        longitude: Double,
        cartItems: List<CartItemDomain>
    ): Order {
        val request = CreateOrderRequestDomain(
            restaurantId = restaurantId,
            address = address,
            latitude = latitude,
            longitude = longitude,
            items = cartItems.map { OrderItemRequestDomain(it.menuItemId, it.quantity) }
        )
        return repository.createOrder(request)
    }
}