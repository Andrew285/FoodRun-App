package com.rainyday.foodrun.feature.restaurant.domain.usecases

import com.rainyday.foodrun.feature.restaurant.domain.model.RestaurantDetailDomain
import com.rainyday.foodrun.feature.restaurant.domain.repository.RestaurantDetailRepository
import javax.inject.Inject

class GetRestaurantByIdUseCase @Inject constructor(
    private val repository: RestaurantDetailRepository
) {
    suspend operator fun invoke(id: String): Result<RestaurantDetailDomain> =
        repository.getRestaurantById(id)
}