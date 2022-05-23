package com.example.currencyratetracker.ui.fragments.popularfragment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.currencyratetracker.domain.models.Currency

class PopularDiffUtils : DiffUtil.ItemCallback<Currency>() {
    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem == newItem
    }
}
