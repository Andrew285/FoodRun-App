package com.rainyday.foodrun.feature.home.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantsApi {
    @GET("restaurants")
    suspend fun getRestaurants(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("category") category: String? = null,
        @Query("search") search: String? = null
    ): RestaurantsResponseDto

    @GET("restaurants/{id}")
    suspend fun getRestaurantById(
        @Path("id") id: String
    ): RestaurantDetailDto
}