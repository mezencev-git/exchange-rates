package com.mezencevsem.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mezencevsem.data.local.dao.FavoriteCurrenciesDao
import com.mezencevsem.data.local.entity.FavoriteCurrency

@Database(entities = [FavoriteCurrency::class], version = 1)
internal abstract class CurrenciesDatabase : RoomDatabase() {

    companion object {
        const val NAME = "CurrenciesDatabase"
    }

    abstract fun favoriteCurrenciesDao(): FavoriteCurrenciesDao
}