package com.example.currencyratetracker.data.db.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorites_entity")
data class FavoritesEntity(
    @PrimaryKey
    val currencyName: String,
    val currencyValue: Double
)
