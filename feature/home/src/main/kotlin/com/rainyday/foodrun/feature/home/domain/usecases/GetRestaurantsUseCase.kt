package com.rainyday.foodrun.feature.home.domain.usecases

import com.rainyday.foodrun.feature.home.domain.model.RestaurantDomain
import com.rainyday.foodrun.feature.home.domain.repository.RestaurantRepository
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(
        page: Int = 1,
        limit: Int = 10,
        category: String? = null,
        search: String? = null
    ): Result<List<RestaurantDomain>> = repository.getRestaurants(page, limit, category, search)
}