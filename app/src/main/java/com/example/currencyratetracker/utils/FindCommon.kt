package com.example.currencyratetracker.utils

fun <T> findCommon(first: List<T>, second: List<T>):List<T> {
    return first.filter(second::contains)
}