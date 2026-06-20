package com.rainyday.foodrun.feature.restaurant.domain.repository

import com.rainyday.foodrun.feature.restaurant.domain.model.RestaurantDetailDomain

interface RestaurantDetailRepository {
    suspend fun getRestaurantById(id: String): Result<RestaurantDetailDomain>
}