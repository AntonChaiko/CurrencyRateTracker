package com.example.currencyratetracker.domain.use_cases

import com.example.currencyratetracker.domain.models.Currency
import com.example.currencyratetracker.domain.repository.CurrencyRepository
import javax.inject.Inject


class AddToFavoritesUseCase @Inject constructor(
    private val repository:CurrencyRepository
) {
    suspend operator fun invoke(currency: Currency){
        repository.insertCurrency(currency)
    }
}