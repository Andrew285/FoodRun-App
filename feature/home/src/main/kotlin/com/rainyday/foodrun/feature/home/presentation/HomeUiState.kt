package com.rainyday.foodrun.feature.home.presentation

import com.rainyday.foodrun.feature.home.domain.model.RestaurantDomain

sealed class HomeUIState {
    data class Success(
        val data: List<RestaurantDomain>,
        val isLoadingMore: Boolean = false,
        val isLastPage: Boolean = false
    ): HomeUIState()
    data class Error(val error: String?): HomeUIState()
    object Loading: HomeUIState()
}