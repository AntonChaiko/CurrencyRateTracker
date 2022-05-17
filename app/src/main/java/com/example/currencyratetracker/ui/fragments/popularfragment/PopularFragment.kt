package com.example.currencyratetracker.ui.fragments.popularfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.currencyratetracker.databinding.PopularFragmentBinding
import com.example.currencyratetracker.ui.fragments.basefragment.BaseFragment

class PopularFragment : BaseFragment<PopularFragmentBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): PopularFragmentBinding = PopularFragmentBinding.inflate(inflater,container,false)


}