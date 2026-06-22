package com.rainyday.foodrun.feature.order.domain.usecases

import com.rainyday.foodrun.feature.order.domain.repository.OrderRepository
import com.rainyday.foodrun.feature.order.domain.model.Order
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(orderId: String): Order =
        repository.getOrderById(orderId)
}