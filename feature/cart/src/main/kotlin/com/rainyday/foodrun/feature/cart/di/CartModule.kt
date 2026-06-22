package com.rainyday.foodrun.feature.cart.di

import android.content.Context
import androidx.room.Room
import com.rainyday.foodrun.feature.cart.data.local.CartDao
import com.rainyday.foodrun.feature.cart.data.local.CartDatabase
import com.rainyday.foodrun.feature.cart.data.repository.CartRepositoryImpl
import com.rainyday.foodrun.feature.cart.domain.repository.CartRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CartDatabaseModule {

    @Provides
    @Singleton
    fun provideCartDatabase(@ApplicationContext context: Context): CartDatabase =
        Room.databaseBuilder(context, CartDatabase::class.java, "cart_database").build()

    @Provides
    @Singleton
    fun provideCartDao(database: CartDatabase): CartDao = database.cartDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class CartModule {
    @Binds
    @Singleton
    abstract fun bindCartRepository(repository: CartRepositoryImpl): CartRepository
}