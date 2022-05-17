package com.example.currencyratetracker.data.remote

import com.example.currencyratetracker.domain.models.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeDataRatesApi {
    @GET("/latest")
    fun getLatestCurrency(@Path("base") baseCurrency: String) : CurrencyResponse
}