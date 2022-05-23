package com.example.currencyratetracker.domain.repository

import com.example.currencyratetracker.domain.models.Currency
import com.example.currencyratetracker.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getLatestCurrency(currency:String) : Flow<Resource<List<Currency>>>
    suspend fun insertCurrency(currency:Currency)
    suspend fun getListOfCurrencies() : List<Currency>
    suspend fun deleteCurrency(currency: String)
}