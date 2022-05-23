package com.example.currencyratetracker.ui.fragments.favoritesfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.currencyratetracker.databinding.FavoritesFragmentBinding
import com.example.currencyratetracker.ui.fragments.basefragment.BaseFragment
import com.example.currencyratetracker.ui.fragments.favoritesfragment.adapter.FavoritesFragmentAdapter
import com.example.currencyratetracker.ui.fragments.popularfragment.PopularState
import com.example.currencyratetracker.ui.fragments.popularfragment.PopularViewModel
import com.example.currencyratetracker.ui.fragments.popularfragment.adapter.PopularFragmentAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FavoritesFragmentBinding>() {


    private val viewModel: FavoritesViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLocalCurrencies()


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                updateUi(state)
            }
        }
    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FavoritesFragmentBinding = FavoritesFragmentBinding.inflate(inflater, container, false)

    private fun updateUi(viewState: FavoritesState) {
        val (currencies, isLoading) = viewState
        binding.loadingProgressBar.isVisible = isLoading
        val adapter = FavoritesFragmentAdapter(
            deleteFromFavorites = {viewModel.deleteFromFavorites(it)}
        )
        adapter.submitList(currencies)
        binding.favoritesRecyclerView.adapter = adapter
    }


}