package com.mezencevsem.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mezencevsem.data.local.dao.CurrenciesDao
import com.mezencevsem.data.local.entity.Currency

@Database(entities = [Currency::class], version = 1)
internal abstract class CurrenciesDatabase : RoomDatabase() {

    companion object {
        const val NAME = "CurrenciesDatabase"
    }

    abstract fun currenciesDao(): CurrenciesDao
}