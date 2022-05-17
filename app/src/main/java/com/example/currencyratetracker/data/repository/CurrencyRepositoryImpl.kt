package com.example.currencyratetracker.data.repository

import com.example.currencyratetracker.data.remote.ExchangeDataRatesApi
import com.example.currencyratetracker.domain.models.CurrencyResponse
import com.example.currencyratetracker.domain.repository.CurrencyRepository
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val exchangeDataRatesApi: ExchangeDataRatesApi
) : CurrencyRepository {

    override suspend fun getLatestCurrency(currency: String): CurrencyResponse {
        return exchangeDataRatesApi.getLatestCurrency(currency)
    }

}