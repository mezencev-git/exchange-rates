package com.mezencevsem.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mezencevsem.data.local.entity.Currency

@Dao
internal interface CurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCurrencies(currencies: List<Currency>)

    @Query("SELECT * FROM currency")
    suspend fun getCurrencies(): List<Currency>

    @Update
    suspend fun updateCurrency(currency: Currency)
}