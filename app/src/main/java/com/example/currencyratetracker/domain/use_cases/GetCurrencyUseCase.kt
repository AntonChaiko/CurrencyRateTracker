package com.example.currencyratetracker.domain.use_cases

import com.example.currencyratetracker.domain.models.Currency
import com.example.currencyratetracker.domain.repository.CurrencyRepository
import com.example.currencyratetracker.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrencyUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    operator fun invoke(currency: String): Flow<Resource<List<Currency>>> {
        return repository.getLatestCurrency(currency)
    }


}