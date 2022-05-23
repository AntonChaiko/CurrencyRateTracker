package com.example.currencyratetracker.data.db.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.currencyratetracker.data.db.local.entity.FavoritesEntity


@Database(
    entities = [FavoritesEntity::class],
    version = 1
)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract val dao: FavoritesDao
}