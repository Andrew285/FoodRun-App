package com.rainyday.foodrun.feature.home.domain.usecases

import com.rainyday.foodrun.feature.home.domain.model.RestaurantDetailDomain
import com.rainyday.foodrun.feature.home.domain.repository.RestaurantRepository
import javax.inject.Inject

class GetRestaurantByIdUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(id: String): Result<RestaurantDetailDomain> =
        repository.getRestaurantById(id)
}