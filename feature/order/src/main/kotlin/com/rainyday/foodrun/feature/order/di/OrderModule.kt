package com.rainyday.foodrun.feature.order.di

import com.rainyday.foodrun.feature.order.data.remote.OrderApi
import com.rainyday.foodrun.feature.order.data.repository.OrderRepositoryImpl
import com.rainyday.foodrun.feature.order.domain.repository.OrderRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderApiModule {

    @Provides
    @Singleton
    fun provideOrderApi(retrofit: Retrofit): OrderApi =
        retrofit.create(OrderApi::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class OrderRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindOrderRepository(impl: OrderRepositoryImpl): OrderRepository
}