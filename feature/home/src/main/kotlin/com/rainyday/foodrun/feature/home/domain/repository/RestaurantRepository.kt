package com.rainyday.foodrun.feature.home.domain.repository

import com.rainyday.foodrun.feature.home.domain.model.RestaurantDetailDomain
import com.rainyday.foodrun.feature.home.domain.model.RestaurantDomain

interface RestaurantRepository {
    suspend fun getRestaurants(
        page: Int,
        limit: Int,
        category: String? = null,
        search: String? = null
    ): Result<List<RestaurantDomain>>


    suspend fun getRestaurantById(id: String): Result<RestaurantDetailDomain>
}