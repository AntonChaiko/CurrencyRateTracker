package com.example.currencyratetracker.data.repository

import com.example.currencyratetracker.data.db.local.FavoritesDao
import com.example.currencyratetracker.data.db.local.FavoritesDatabase
import com.example.currencyratetracker.data.mapper.asCurrencies
import com.example.currencyratetracker.data.mapper.asCurrenciesFromDb
import com.example.currencyratetracker.data.mapper.asFavoritesEntity
import com.example.currencyratetracker.data.remote.ExchangeDataRatesApi
import com.example.currencyratetracker.domain.models.Currency
import com.example.currencyratetracker.domain.repository.CurrencyRepository
import com.example.currencyratetracker.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CurrencyRepositoryImpl @Inject constructor(
    private val exchangeDataRatesApi: ExchangeDataRatesApi,
    private val favoritesDao: FavoritesDao,
    private val dispatchers: com.example.currencyratetracker.data.Dispatchers
) : CurrencyRepository {

    override fun getLatestCurrency(currency: String): Flow<Resource<List<Currency>>> = flow {
        emit(Resource.Loading())
        val remoteCurrencies = exchangeDataRatesApi.getLatestCurrency(currency).asCurrencies()
        val localCurrencies = favoritesDao.getAllCurrencies().asCurrencies()
        localCurrencies.forEach { asd ->
            remoteCurrencies.find { it.name == asd.name }?.isFavorite = true
        }
        emit(Resource.Success(remoteCurrencies))

    }.catch { failure -> emit(Resource.Error(failure.localizedMessage ?: "An unexpected error")) }
        .flowOn(dispatchers.dispatcher())

    override suspend fun insertCurrency(currency: Currency) {
        withContext(dispatchers.dispatcher()) {
            favoritesDao.insertCurrency(currency.asFavoritesEntity())
        }
    }

    override suspend fun getListOfCurrencies(): List<Currency> {
        return withContext(dispatchers.dispatcher()) {
            favoritesDao.getAllCurrencies().asCurrenciesFromDb()
        }
    }

    override suspend fun deleteCurrency(currency: String) {
        withContext(dispatchers.dispatcher()) {
            favoritesDao.deleteCurrency(currency)
        }
    }

}