package com.rainyday.foodrun.feature.restaurant.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantDetailApi {
    @GET("restaurants/{id}")
    suspend fun getRestaurantById(@Path("id") id: String): RestaurantDetailDto
}