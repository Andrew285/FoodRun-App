package com.rainyday.foodrun.feature.restaurant.data.repository

import com.rainyday.foodrun.core.domain.model.MenuItemDomain
import com.rainyday.foodrun.feature.restaurant.data.remote.RestaurantDetailApi
import com.rainyday.foodrun.feature.restaurant.domain.model.RestaurantDetailDomain
import com.rainyday.foodrun.feature.restaurant.domain.repository.RestaurantDetailRepository
import javax.inject.Inject

class RestaurantDetailRepositoryImpl @Inject constructor(
    private val api: RestaurantDetailApi
) : RestaurantDetailRepository {

    override suspend fun getRestaurantById(id: String): Result<RestaurantDetailDomain> =
        runCatching {
            val dto = api.getRestaurantById(id)
            val menuItems = dto.menuItems
                .filter { it.isAvailable }
                .map { item ->
                    MenuItemDomain(
                        id = item.id,
                        name = item.name,
                        description = item.description,
                        price = item.price,
                        imageUrl = item.imageUrl,
                        category = item.category
                    )
                }
            RestaurantDetailDomain(
                id = dto.id,
                name = dto.name,
                description = dto.description,
                imageUrl = dto.imageUrl,
                rating = dto.rating,
                address = dto.address,
                category = dto.category,
                isOpen = dto.isOpen,
                menuItems = menuItems,
                latitude = dto.latitude,
                longitude = dto.longitude,
            )
        }
}