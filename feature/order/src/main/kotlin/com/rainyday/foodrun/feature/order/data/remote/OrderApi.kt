package com.rainyday.foodrun.feature.order.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApi {
    @POST("orders")
    suspend fun createOrder(@Body request: CreateOrderRequest): OrderResponse

    @GET("orders/{id}")
    suspend fun getOrderById(@Path("id") orderId: String): OrderResponse
}