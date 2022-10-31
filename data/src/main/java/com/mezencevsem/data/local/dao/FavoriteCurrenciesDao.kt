package com.mezencevsem.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mezencevsem.data.local.entity.FavoriteCurrency

@Dao
internal interface FavoriteCurrenciesDao {

    @Query("SELECT * FROM favoritecurrency")
    suspend fun getFavoriteCurrencies(): List<FavoriteCurrency>

    @Insert
    suspend fun addFavoriteCurrency(currency: FavoriteCurrency)

    @Delete
    suspend fun removeFavoriteCurrency(currency: FavoriteCurrency)
}