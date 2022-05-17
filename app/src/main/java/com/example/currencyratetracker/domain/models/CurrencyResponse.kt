package com.example.currencyratetracker.domain.models

import android.util.ArrayMap

data class CurrencyResponse(
    val base: String,
    val date: String,
    val rates: ArrayMap<String, Double>,
    val success: Boolean,
    val timestamp: Int
)