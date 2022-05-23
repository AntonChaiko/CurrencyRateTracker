package com.example.currencyratetracker.data.repository

import android.util.Log
import com.example.currencyratetracker.data.db.local.FavoritesDatabase
import com.example.currencyratetracker.data.db.local.entity.FavoritesEntity
import com.example.currencyratetracker.data.mapper.asCurrencies
import com.example.currencyratetracker.data.mapper.asCurrenciesFromDb
import com.example.currencyratetracker.data.mapper.asFavoritesEntity
import com.example.currencyratetracker.data.remote.ExchangeDataRatesApi
import com.example.currencyratetracker.domain.models.Currency
import com.example.currencyratetracker.domain.repository.CurrencyRepository
import com.example.currencyratetracker.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CurrencyRepositoryImpl @Inject constructor(
    private val exchangeDataRatesApi: ExchangeDataRatesApi,
    private val favoritesDatabase: FavoritesDatabase
) : CurrencyRepository {

    override fun getLatestCurrency(currency: String): Flow<Resource<List<Currency>>> = flow {
        try {
            emit(Resource.Loading())

            val remoteCurrencies = exchangeDataRatesApi.getLatestCurrency(currency).asCurrencies()
            val localCurrencies = favoritesDatabase.dao.getAllCurrencies().asCurrencies()

            localCurrencies.forEach { asd ->
                remoteCurrencies.find { it.name == asd.name }?.isFavorite = true
            }

            emit(Resource.Success(remoteCurrencies))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
        }

    }

    override suspend fun insertCurrency(currency: Currency) {
        withContext(Dispatchers.IO) {
            favoritesDatabase.dao.insertCurrency(currency.asFavoritesEntity())
        }
    }

    override suspend fun getListOfCurrencies(): List<Currency> {
        return withContext(Dispatchers.IO) {
            favoritesDatabase.dao.getAllCurrencies().asCurrenciesFromDb()
        }
    }

    override suspend fun deleteCurrency(currency: String) {
        withContext(Dispatchers.IO){
            favoritesDatabase.dao.deleteCurrency(currency)
        }
    }

}