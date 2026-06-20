package com.rainyday.foodrun.feature.home.di

import com.rainyday.foodrun.feature.home.data.remote.RestaurantsApi
import com.rainyday.foodrun.feature.home.data.repository.RestaurantRepositoryImpl
import com.rainyday.foodrun.feature.home.domain.repository.RestaurantRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RestaurantModule {
    @Binds
    @Singleton
    abstract fun bindsRestaurantRepository(impl: RestaurantRepositoryImpl): RestaurantRepository

    companion object {
        @Provides
        @Singleton
        fun provideRestaurantApiService(retrofit: Retrofit): RestaurantsApi {
            return retrofit.create(RestaurantsApi::class.java)
        }
    }
}