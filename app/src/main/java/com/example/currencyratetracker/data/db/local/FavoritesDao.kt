package com.example.currencyratetracker.data.db.local

import androidx.room.*
import com.example.currencyratetracker.data.db.local.entity.FavoritesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currency: FavoritesEntity)

    @Query("DELETE FROM favorites_entity WHERE currencyName IN(:currency)")
    suspend fun deleteCurrency(currency: String)

    @Query("select * from favorites_entity")
    suspend fun getAllCurrencies(): List<FavoritesEntity>
}