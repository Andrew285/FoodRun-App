package com.rainyday.foodrun.feature.cart.domain.usecases

import com.rainyday.foodrun.core.domain.model.CartItemDomain
import com.rainyday.foodrun.core.domain.repository.CartRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(item: CartItemDomain) = repository.addItem(item)
}