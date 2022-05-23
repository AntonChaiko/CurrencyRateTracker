package com.example.currencyratetracker.ui.fragments.favoritesfragment

import com.example.currencyratetracker.domain.models.Currency

data class FavoritesState(
    val currencies:List<Currency> = emptyList(),
    val isLoading:Boolean = false
) {
}