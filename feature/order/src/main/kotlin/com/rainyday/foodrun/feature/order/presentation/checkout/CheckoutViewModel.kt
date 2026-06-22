package com.rainyday.foodrun.feature.order.presentation.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rainyday.foodrun.core.domain.repository.CartRepository
import com.rainyday.foodrun.feature.order.domain.model.Order
import com.rainyday.foodrun.feature.order.domain.usecases.CreateOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CheckoutUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val order: Order? = null,
    val address: String = "",
    val totalItems: Int = 0,
    val subtotal: Double = 0.0
) {
    val deliveryFee = 50.0
    val total: Double get() = subtotal + deliveryFee
}

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val createOrder: CreateOrderUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CheckoutUiState())
    val uiState: StateFlow<CheckoutUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            cartRepository.getCartItems().collect { items ->
                _uiState.update {
                    it.copy(
                        subtotal = items.sumOf { item -> item.totalPrice },
                        totalItems = items.sumOf { item -> item.quantity }
                    )
                }
            }
        }
    }

    fun onAddressChange(address: String) {
        _uiState.update { it.copy(address = address) }
    }

    fun placeOrder(restaurantId: String) = viewModelScope.launch {
        val state = _uiState.value
        if (state.address.isBlank()) {
            _uiState.update { it.copy(error = "Введіть адресу доставки") }
            return@launch
        }

        _uiState.update { it.copy(isLoading = true, error = null) }

        runCatching {
            val cartItems = cartRepository.getCartItems().first()
            createOrder(
                restaurantId = restaurantId,
                address = state.address,
                latitude = 50.45,
                longitude = 30.52,
                cartItems = cartItems
            )
        }.onSuccess { order ->
            cartRepository.clearCart()
            _uiState.update { it.copy(isLoading = false, order = order) }
        }.onFailure { error ->
            _uiState.update { it.copy(isLoading = false, error = error.message) }
        }
    }
}