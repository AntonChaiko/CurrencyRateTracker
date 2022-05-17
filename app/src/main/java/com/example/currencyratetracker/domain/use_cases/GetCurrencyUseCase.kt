package com.example.currencyratetracker.domain.use_cases

import com.example.currencyratetracker.domain.models.CurrencyResponse
import com.example.currencyratetracker.domain.repository.CurrencyRepository
import com.example.currencyratetracker.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCurrencyUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    operator fun invoke(currency: String): Flow<Resource<CurrencyResponse>> = flow {

        try {
            emit(Resource.Loading())

            val responseCurrency = repository.getLatestCurrency(currency)
            emit(Resource.Success(responseCurrency))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Something goes wrong"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Something goes wrong"))
        }
    }
}