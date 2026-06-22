package com.rainyday.foodrun.feature.cart.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items")
    fun getAllItems(): Flow<List<CartEntity>>

    @Query("SELECT SUM(quantity) FROM cart_items")
    fun getTotalItemCount(): Flow<Int?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(cartItem: CartEntity)

    @Query("DELETE FROM cart_items WHERE menuItemId = :menuItemId")
    suspend fun deleteItem(menuItemId: String)

    @Query("UPDATE cart_items SET quantity = :quantity WHERE menuItemId = :menuItemId")
    suspend fun updateQuantity(menuItemId: String, quantity: Int)

    @Query("DELETE FROM cart_items")
    suspend fun clearAll()
}