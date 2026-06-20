package com.rainyday.foodrun.feature.home.data.repository

import com.rainyday.foodrun.feature.home.data.remote.RestaurantsApi
import com.rainyday.foodrun.feature.home.domain.model.MenuItemDomain
import com.rainyday.foodrun.feature.home.domain.model.RestaurantDetailDomain
import com.rainyday.foodrun.feature.home.domain.model.RestaurantDomain
import com.rainyday.foodrun.feature.home.domain.repository.RestaurantRepository
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val api: RestaurantsApi
): RestaurantRepository {
    override suspend fun getRestaurants(
        page: Int,
        limit: Int,
        category: String?,
        search: String?
    ): Result<List<RestaurantDomain>> = runCatching {
        api.getRestaurants(page, limit, category, search).data.map { dto ->
            RestaurantDomain(
                id = dto.id,
                name = dto.name,
                description = dto.description,
                imageUrl = dto.imageUrl,
                rating = dto.rating,
                address = dto.address,
                category = dto.category,
                isOpen = dto.isOpen,
                latitude = dto.latitude,
                longitude = dto.longitude
            )
        }
    }

    override suspend fun getRestaurantById(id: String): Result<RestaurantDetailDomain> = runCatching {
        val dto = api.getRestaurantById(id)
        RestaurantDetailDomain(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            imageUrl = dto.imageUrl,
            rating = dto.rating,
            address = dto.address,
            category = dto.category,
            isOpen = dto.isOpen,
            latitude = dto.latitude,
            longitude = dto.longitude,
            menuItems = dto.menuItems.map { item ->
                MenuItemDomain(
                    id = item.id,
                    name = item.name,
                    description = item.description,
                    price = item.price,
                    imageUrl = item.imageUrl,
                    category = item.category,
                    isAvailable = item.isAvailable
                )
            }
        )
    }
}