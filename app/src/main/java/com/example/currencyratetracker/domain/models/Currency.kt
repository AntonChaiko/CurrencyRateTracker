package com.example.currencyratetracker.domain.models

data class Currency(
    val name: String = "USD",
    val price: Double,
    var isFavorite: Boolean = false
)
