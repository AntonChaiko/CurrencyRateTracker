package com.example.currencyratetracker.data.db.local

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.currencyratetracker.data.db.local.entity.FavoritesEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class FavoritesDaoTest {

    private lateinit var database: FavoritesDatabase
    private lateinit var dao: FavoritesDao

    @Before
    fun setup() {

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavoritesDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.dao

    }

    @Test
    fun insertCurrency() = runTest {
        val favEntity = FavoritesEntity("USD", 23.3)
        dao.insertCurrency(favEntity)
        val allItems = dao.getAllCurrencies()
        assertThat(allItems).contains(favEntity)
    }

    @Test
    fun deleteCurrency() = runTest{
        val favEntity = FavoritesEntity("USD", 23.3)
        dao.insertCurrency(favEntity)
        dao.deleteCurrency("USD")
        val allItems = dao.getAllCurrencies()
        assertThat(allItems).doesNotContain(favEntity)
    }

    @Test
    fun listSize() = runTest{
        val favEntity1 = FavoritesEntity("BYN", 2433.3)
        val favEntity2 = FavoritesEntity("USD", 6323123.3)
        val favEntity3 = FavoritesEntity("EUR", 22343.3)
        val favEntity4 = FavoritesEntity("QCD", 22343.3)

        dao.insertCurrency(favEntity1)
        dao.insertCurrency(favEntity2)
        dao.insertCurrency(favEntity3)
        dao.insertCurrency(favEntity4)
        val allItems = dao.getAllCurrencies()
        assertThat(allItems.size).isEqualTo(4)

    }

    @After
    fun closeDb(){
        database.close()
    }

}