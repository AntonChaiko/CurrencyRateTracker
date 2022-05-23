package com.example.currencyratetracker.ui.fragments.popularfragment

import com.example.currencyratetracker.domain.models.Currency

data class PopularState(
    val currencies:List<Currency> = emptyList(),
    val isLoading:Boolean = false
)