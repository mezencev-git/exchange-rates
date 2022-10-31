package com.mezencevsem.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class FavoriteCurrency(
    @PrimaryKey val code: String,
    @ColumnInfo(name = "name") val name: String
)