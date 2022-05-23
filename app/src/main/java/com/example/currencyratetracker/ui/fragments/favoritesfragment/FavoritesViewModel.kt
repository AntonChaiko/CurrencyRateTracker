package com.example.currencyratetracker.ui.fragments.favoritesfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyratetracker.domain.use_cases.DeleteFromFavoritesUseCase
import com.example.currencyratetracker.domain.use_cases.GetLocalCurrenciesUseCase
import com.example.currencyratetracker.ui.fragments.popularfragment.PopularState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getLocalCurrencies: GetLocalCurrenciesUseCase,
    private val deleteFromFavorites: DeleteFromFavoritesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FavoritesState())
    val state: StateFlow<FavoritesState> get() = _state

    fun getLocalCurrencies() {
        viewModelScope.launch {
            _state.value = FavoritesState(isLoading = true)
            _state.value = FavoritesState(getLocalCurrencies.invoke())
        }
    }
    fun deleteFromFavorites(currency: String){
        viewModelScope.launch {
            deleteFromFavorites.invoke(currency)
            _state.value = FavoritesState(getLocalCurrencies.invoke())
        }
    }
}