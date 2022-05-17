package com.example.currencyratetracker.domain.repository

import com.example.currencyratetracker.domain.models.CurrencyResponse

interface CurrencyRepository {
    suspend fun getLatestCurrency(currency:String) : CurrencyResponse
}