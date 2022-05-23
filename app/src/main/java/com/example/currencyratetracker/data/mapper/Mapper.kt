package com.example.currencyratetracker.data.mapper

import com.example.currencyratetracker.data.db.local.entity.FavoritesEntity
import com.example.currencyratetracker.domain.models.Currency
import com.example.currencyratetracker.domain.models.CurrencyResponse

fun CurrencyResponse.asCurrencies(): List<Currency> {
    val currenciesList = mutableListOf<Currency>()
    this.rates.forEach {
        currenciesList.add(Currency(name = it.key, price = it.value, isFavorite = false))
    }
    return currenciesList
}

fun List<FavoritesEntity>.asCurrencies(): List<Currency> {
    return map {
        Currency(
            name = it.currencyName,
            price = it.currencyValue,
            isFavorite = false
            )
    }
}
fun List<FavoritesEntity>.asCurrenciesFromDb(): List<Currency> {
    return map {
        Currency(
            name = it.currencyName,
            price = it.currencyValue,
            isFavorite = true
        )
    }
}
fun Currency.asFavoritesEntity(): FavoritesEntity {
    return FavoritesEntity(name, price)

}