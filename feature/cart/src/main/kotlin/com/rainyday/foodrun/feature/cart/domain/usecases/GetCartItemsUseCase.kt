package com.rainyday.foodrun.feature.cart.domain.usecases

import com.rainyday.foodrun.feature.cart.domain.repository.CartRepository
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor(
    private val repository: CartRepository
) {
    operator fun invoke() = repository.getCartItems()
}