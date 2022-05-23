package com.example.currencyratetracker.ui.fragments.popularfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyratetracker.R
import com.example.currencyratetracker.databinding.CurrencyItemBinding
import com.example.currencyratetracker.domain.models.Currency

class PopularFragmentAdapter(
    val addCurrencyToFavorite: (currency: Currency) -> Unit,
    val deleteFromFavorites: (currency: String) -> Unit
) : ListAdapter<Currency, PopularFragmentAdapter.PopularViewHolder>(PopularDiffUtils()) {

    inner class PopularViewHolder(
        private val binding: CurrencyItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Currency) {
            val (name, price, isFavorite) = currency
            with(binding) {
                currencyPrice.text = price.toString()
                currencyName.text = name
                addToFavoriteButton.apply {
                    setOnClickListener {
                        if (isFavorite) {
                            deleteFromFavorites(name)
                            setImageResource(R.drawable.ic_favorites)
                        } else {
                            addCurrencyToFavorite(currency)
                            setImageResource(R.drawable.ic_favorites_yellow)
                        }

                    }
                    if (isFavorite) setImageResource(R.drawable.ic_favorites_yellow) else setImageResource(
                        R.drawable.ic_favorites
                    )
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(
            CurrencyItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}