package com.rainyday.foodrun.feature.order.presentation.order_tracking

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rainyday.foodrun.feature.order.domain.model.Order
import com.rainyday.foodrun.feature.order.domain.model.OrderStatus
import com.rainyday.foodrun.feature.order.domain.usecases.GetOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class OrderTrackingUiState(
    val isLoading: Boolean = true,
    val order: Order? = null,
    val error: String? = null
) {
    val isDelivered: Boolean get() = order?.status == OrderStatus.DELIVERED
    val isCancelled: Boolean get() = order?.status == OrderStatus.CANCELLED
}

@HiltViewModel
class OrderTrackingViewModel @Inject constructor(
    private val getOrder: GetOrderUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val orderId: String = checkNotNull(savedStateHandle["orderId"])

    private val _uiState = MutableStateFlow(OrderTrackingUiState())
    val uiState: StateFlow<OrderTrackingUiState> = _uiState.asStateFlow()

    private var pollingJob: Job? = null

    init {
        startPolling()
    }

    private fun startPolling() {
        pollingJob = viewModelScope.launch {
            while (isActive) {
                fetchOrder()
                val state = _uiState.value
                // Зупиняємо polling якщо фінальний статус
                if (state.isDelivered || state.isCancelled) break
                delay(10_000) // кожні 10 секунд
            }
        }
    }

    private suspend fun fetchOrder() {
        runCatching { getOrder(orderId) }
            .onSuccess { order ->
                _uiState.update { it.copy(isLoading = false, order = order, error = null) }
            }
            .onFailure { error ->
                _uiState.update { it.copy(isLoading = false, error = error.message) }
            }
    }

    override fun onCleared() {
        super.onCleared()
        pollingJob?.cancel()
    }
}