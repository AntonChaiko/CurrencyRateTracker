package com.example.currencyratetracker.ui.fragments.popularfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.currencyratetracker.R
import com.example.currencyratetracker.databinding.PopularFragmentBinding
import com.example.currencyratetracker.ui.fragments.basefragment.BaseFragment

class PopularFragment : BaseFragment<PopularFragmentBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayAdapter = ArrayAdapter(
            view.context, android.R.layout.simple_list_item_1, resources.getStringArray(R.array.currencies)
        )
        binding.currenciesTextView.setAdapter(arrayAdapter)

    }
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): PopularFragmentBinding = PopularFragmentBinding.inflate(inflater,container,false)


}