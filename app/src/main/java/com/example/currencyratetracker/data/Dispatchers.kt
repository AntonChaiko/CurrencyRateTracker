package com.example.currencyratetracker.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface Dispatchers {
    fun dispatcher(): CoroutineDispatcher

    class Main() : com.example.currencyratetracker.data.Dispatchers {
        override fun dispatcher(): CoroutineDispatcher = Dispatchers.Main
    }

    class Io() : com.example.currencyratetracker.data.Dispatchers {
        override fun dispatcher(): CoroutineDispatcher = Dispatchers.IO
    }

    class Default() : com.example.currencyratetracker.data.Dispatchers {
        override fun dispatcher(): CoroutineDispatcher = Dispatchers.Default
    }

    class Unconfined() : com.example.currencyratetracker.data.Dispatchers {
        override fun dispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
    }
}