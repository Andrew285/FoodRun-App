package com.rainyday.foodrun.feature.cart.domain.usecases

import com.rainyday.foodrun.feature.cart.domain.repository.CartRepository
import javax.inject.Inject

class UpdateQuantityUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(menuItemId: String, quantity: Int) {
        if (quantity <= 0) repository.removeItem(menuItemId)
        else repository.updateQuantity(menuItemId, quantity)
    }
}