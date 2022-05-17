package com.example.currencyratetracker.ui.fragments.favoritesfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.currencyratetracker.databinding.FavoritesFragmentBinding
import com.example.currencyratetracker.ui.fragments.basefragment.BaseFragment

class FavoritesFragment : BaseFragment<FavoritesFragmentBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }





    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FavoritesFragmentBinding = FavoritesFragmentBinding.inflate(inflater, container, false)


}