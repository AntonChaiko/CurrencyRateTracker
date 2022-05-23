package com.example.currencyratetracker.ui.fragments.popularfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.currencyratetracker.R
import com.example.currencyratetracker.databinding.PopularFragmentBinding
import com.example.currencyratetracker.domain.models.Currency
import com.example.currencyratetracker.ui.fragments.basefragment.BaseFragment
import com.example.currencyratetracker.ui.fragments.popularfragment.adapter.PopularFragmentAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class PopularFragment : BaseFragment<PopularFragmentBinding>() {

    private val viewModel: PopularViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayAdapter = ArrayAdapter(
            view.context,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.currencies)
        )



        binding.currenciesTextView.apply {
            this.setAdapter(arrayAdapter)
            this.setOnFocusChangeListener { _, _ ->
                binding.currenciesTextView.showDropDown()
            }
            this.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                val x = arrayAdapter.getItem(position)
                binding.currenciesTextView.clearFocus()
                hideKeyboard()
                viewModel.getLatestCurrency(x.toString())
            }
        }


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                updateUi(state)
            }
        }
    }

    private fun updateUi(viewState: PopularState) {
        val (currencies, isLoading) = viewState
        binding.loadingProgressBar.isVisible = isLoading
        val adapter = PopularFragmentAdapter(
            addCurrencyToFavorite = { viewModel.addToFavorites(it) },
            deleteFromFavorites = {viewModel.deleteFromFavorites(it)}
        )
        adapter.submitList(currencies)
        binding.currenciesRecyclerView.adapter = adapter
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): PopularFragmentBinding = PopularFragmentBinding.inflate(inflater, container, false)


}