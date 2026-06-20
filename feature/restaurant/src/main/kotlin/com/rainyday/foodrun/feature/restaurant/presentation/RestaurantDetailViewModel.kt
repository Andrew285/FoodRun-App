package com.rainyday.foodrun.feature.restaurant.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rainyday.foodrun.feature.restaurant.domain.model.RestaurantDetailDomain
import com.rainyday.foodrun.feature.restaurant.domain.usecases.GetRestaurantByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailViewModel @Inject constructor(
    private val getRestaurantDetailUseCase: GetRestaurantByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val restaurantId: String = checkNotNull(savedStateHandle["restaurantId"])

    private val _state = MutableStateFlow<RestaurantDetailState>(RestaurantDetailState.Loading)
    val state: StateFlow<RestaurantDetailState> = _state.asStateFlow()

    init {
        loadRestaurant()
    }

    fun loadRestaurant() {
        viewModelScope.launch {
            _state.value = RestaurantDetailState.Loading
            getRestaurantDetailUseCase(restaurantId)
                .onSuccess { _state.value = RestaurantDetailState.Success(it) }
                .onFailure { _state.value = RestaurantDetailState.Error(it.message ?: "Unknown error") }
        }
    }
}