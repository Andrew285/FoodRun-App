package com.rainyday.foodrun.feature.cart.domain.usecases

import com.rainyday.foodrun.feature.cart.domain.repository.CartRepository
import javax.inject.Inject

class RemoveFromCartUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(menuItemId: String) = repository.removeItem(menuItemId)
}