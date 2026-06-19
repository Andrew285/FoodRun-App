package com.rainyday.foodrun.core.network.api

import com.rainyday.foodrun.core.network.model.RestaurantDetailDto
import com.rainyday.foodrun.core.network.model.RestaurantsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodRunApi {
    @GET("restaurants")
    suspend fun getRestaurants(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("category") category: String? = null,
        @Query("search") search: String? = null
    ): RestaurantsResponseDto

    @GET("restaurants/{id}")
    suspend fun getRestaurantById(@Path("id") id: String): RestaurantDetailDto
}