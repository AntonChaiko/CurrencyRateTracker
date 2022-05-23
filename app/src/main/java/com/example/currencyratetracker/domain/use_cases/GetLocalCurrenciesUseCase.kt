package com.example.currencyratetracker.domain.use_cases

import com.example.currencyratetracker.domain.models.Currency
import com.example.currencyratetracker.domain.repository.CurrencyRepository
import javax.inject.Inject

class GetLocalCurrenciesUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    suspend operator fun invoke(): List<Currency> {
        return repository.getListOfCurrencies()
    }
}