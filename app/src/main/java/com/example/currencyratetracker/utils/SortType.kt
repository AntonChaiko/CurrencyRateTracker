package com.example.currencyratetracker.utils

sealed class SortType {
    object Ascending : SortType()
    object Descending : SortType()
}