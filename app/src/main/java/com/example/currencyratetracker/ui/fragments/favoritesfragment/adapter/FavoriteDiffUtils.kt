package com.example.currencyratetracker.ui.fragments.favoritesfragment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.currencyratetracker.domain.models.Currency

class FavoriteDiffUtils : DiffUtil.ItemCallback<Currency>() {
    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem == newItem
    }
}
