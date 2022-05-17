package com.example.currencyratetracker.ui.fragments.basefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.currencyratetracker.utils.hideKeyboard
import com.example.currencyratetracker.utils.toast

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    private var _binding: T? = null
    protected val binding get() = checkNotNull(_binding)


    protected abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): T

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = initBinding(inflater, container)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun toast(message: String) {
        view?.let { activity?.toast(message) }
    }
}