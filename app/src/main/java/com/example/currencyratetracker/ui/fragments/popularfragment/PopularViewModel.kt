package com.example.currencyratetracker.ui.fragments.popularfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyratetracker.domain.models.Currency
import com.example.currencyratetracker.domain.use_cases.AddToFavoritesUseCase
import com.example.currencyratetracker.domain.use_cases.DeleteFromFavoritesUseCase
import com.example.currencyratetracker.domain.use_cases.GetCurrencyUseCase
import com.example.currencyratetracker.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PopularViewModel @Inject constructor(
    private val getCurrency: GetCurrencyUseCase,
    private val addToFavorites: AddToFavoritesUseCase,
    private val deleteFromFavorites: DeleteFromFavoritesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PopularState())
    val state: StateFlow<PopularState> get() = _state

    private var getCurrencies: Job? = null

    fun getLatestCurrency(currency: String) {
        getCurrencies?.cancel()
        getCurrencies = getCurrency(currency.trim()).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _state.value = PopularState(
                        currencies = resource.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error -> {
                    _state.value = PopularState(
                        currencies = resource.data ?: emptyList(),
                        isLoading = false
                    )

                }
                is Resource.Success -> {
                    _state.value = PopularState(
                        currencies = resource.data ?: emptyList(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addToFavorites(currency: Currency) {
        viewModelScope.launch {
            addToFavorites.invoke(currency)
        }

    }
    fun deleteFromFavorites(currency: String){
        viewModelScope.launch {
            deleteFromFavorites.invoke(currency)
        }
    }

}