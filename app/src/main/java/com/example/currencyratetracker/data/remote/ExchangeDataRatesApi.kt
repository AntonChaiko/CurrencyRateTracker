package com.example.currencyratetracker.data.remote

import com.example.currencyratetracker.domain.models.CurrencyResponse
import com.example.currencyratetracker.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ExchangeDataRatesApi {

    @Headers("apikey:${Constants.API_KEY}")
    @GET("latest")
    suspend fun getLatestCurrency(@Query("base") baseCurrency: String): CurrencyResponse
}