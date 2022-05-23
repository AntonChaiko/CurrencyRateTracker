package com.example.currencyratetracker.ui.fragments.favoritesfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyratetracker.R
import com.example.currencyratetracker.databinding.CurrencyItemBinding
import com.example.currencyratetracker.domain.models.Currency
import com.example.currencyratetracker.ui.fragments.popularfragment.adapter.PopularDiffUtils

class FavoritesFragmentAdapter(
    val deleteFromFavorites: (currency: String) -> Unit
) : ListAdapter<Currency, FavoritesFragmentAdapter.FavoritesViewHolder>(PopularDiffUtils()) {

    inner class FavoritesViewHolder(
        private val binding: CurrencyItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Currency) {
            val (name, price, isFavorite) = currency
            with(binding) {
                currencyPrice.text = price.toString()
                currencyName.text = name
                addToFavoriteButton.apply {
                    setImageResource(R.drawable.ic_favorites_yellow)
                    setOnClickListener {
                        deleteFromFavorites(name)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            CurrencyItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}