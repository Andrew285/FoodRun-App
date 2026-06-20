package com.rainyday.foodrun.feature.restaurant.di

import com.rainyday.foodrun.feature.restaurant.data.remote.RestaurantDetailApi
import com.rainyday.foodrun.feature.restaurant.data.repository.RestaurantDetailRepositoryImpl
import com.rainyday.foodrun.feature.restaurant.domain.repository.RestaurantDetailRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RestaurantDetailModule {

    @Binds
    @Singleton
    abstract fun bindRestaurantDetailRepository(
        impl: RestaurantDetailRepositoryImpl
    ): RestaurantDetailRepository

    companion object {
        @Provides
        @Singleton
        fun provideRestaurantDetailApiService(retrofit: Retrofit): RestaurantDetailApi =
            retrofit.create(RestaurantDetailApi::class.java)
    }
}