package com.rainyday.foodrun.feature.cart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rainyday.foodrun.core.domain.model.CartItemDomain
import com.rainyday.foodrun.feature.cart.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CartUiState(
    val items: List<CartItemDomain> = emptyList(),
    val isLoading: Boolean = false
) {
    val totalPrice: Double get() = items.sumOf { it.totalPrice }
    val totalItems: Int get() = items.sumOf { it.quantity }
    val isEmpty: Boolean get() = items.isEmpty()
}

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItems: GetCartItemsUseCase,
    private val addToCart: AddToCartUseCase,
    private val removeFromCart: RemoveFromCartUseCase,
    private val updateQuantity: UpdateQuantityUseCase,
    private val clearCart: ClearCartUseCase
) : ViewModel() {

    val uiState: StateFlow<CartUiState> = getCartItems()
        .map { items -> CartUiState(items = items) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CartUiState(isLoading = true)
        )

    val restaurantId: String?
        get() = uiState.value.items.firstOrNull()?.restaurantId

    fun addItem(item: CartItemDomain) = viewModelScope.launch {
        val existing = uiState.value.items.find { it.menuItemId == item.menuItemId }
        if (existing != null) {
            updateQuantity(item.menuItemId, existing.quantity + 1)
        } else {
            addToCart(item)
        }
    }

    fun removeItem(menuItemId: String) = viewModelScope.launch {
        removeFromCart(menuItemId)
    }

    fun incrementQuantity(menuItemId: String) = viewModelScope.launch {
        val current = uiState.value.items.find { it.menuItemId == menuItemId }?.quantity ?: return@launch
        updateQuantity(menuItemId, current + 1)
    }

    fun decrementQuantity(menuItemId: String) = viewModelScope.launch {
        val current = uiState.value.items.find { it.menuItemId == menuItemId }?.quantity ?: return@launch
        updateQuantity(menuItemId, current - 1)
    }

    fun clearCart() = viewModelScope.launch { clearCart.invoke() }
}