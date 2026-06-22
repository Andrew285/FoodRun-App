package com.rainyday.foodrun.feature.order.data.repository

import com.rainyday.foodrun.feature.order.data.remote.OrderApi
import com.rainyday.foodrun.feature.order.data.remote.OrderResponse
import com.rainyday.foodrun.feature.order.data.remote.toEntity
import com.rainyday.foodrun.feature.order.domain.model.CreateOrderRequestDomain
import com.rainyday.foodrun.feature.order.domain.model.Order
import com.rainyday.foodrun.feature.order.domain.model.OrderItem
import com.rainyday.foodrun.feature.order.domain.model.OrderStatus
import com.rainyday.foodrun.feature.order.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val api: OrderApi
) : OrderRepository {

    override suspend fun createOrder(request: CreateOrderRequestDomain): Order =
        api.createOrder(request.toEntity()).toDomain()

    override suspend fun getOrderById(orderId: String): Order =
        api.getOrderById(orderId).toDomain()

    private fun OrderResponse.toDomain() = Order(
        id = id,
        status = runCatching { OrderStatus.valueOf(status) }.getOrDefault(OrderStatus.PENDING),
        totalPrice = totalPrice,
        address = address,
        createdAt = createdAt,
        restaurantId = restaurantId,
        items = items.map {
            OrderItem(
                id = it.id,
                menuItemId = it.menuItemId,
                quantity = it.quantity,
                price = it.price
            )
        }
    )
}