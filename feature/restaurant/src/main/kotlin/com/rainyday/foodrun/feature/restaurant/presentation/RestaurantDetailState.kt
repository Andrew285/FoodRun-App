package com.rainyday.foodrun.feature.restaurant.presentation

import com.rainyday.foodrun.feature.restaurant.domain.model.RestaurantDetailDomain

sealed class RestaurantDetailState {
    object Loading : RestaurantDetailState()
    data class Success(val restaurant: RestaurantDetailDomain) : RestaurantDetailState()
    data class Error(val message: String) : RestaurantDetailState()
}