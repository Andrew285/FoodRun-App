package com.rainyday.foodrun.feature.order.domain.repository

import com.rainyday.foodrun.feature.order.domain.model.CreateOrderRequestDomain
import com.rainyday.foodrun.feature.order.domain.model.Order

interface OrderRepository {
    suspend fun createOrder(request: CreateOrderRequestDomain): Order
    suspend fun getOrderById(orderId: String): Order
}