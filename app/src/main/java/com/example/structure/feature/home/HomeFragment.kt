package com.example.structure.feature.home

import android.os.Bundle
import android.view.View
import com.example.structure.BuildConfig
import com.example.structure.R
import com.example.structure.databinding.FragmentHomeBinding
import com.example.structure.presentation.feature.home.HomeViewModel
import com.example.structure.uibase.extend.viewBinding
import com.example.structure.uibase.fragment.BaseFragment
import com.example.structure.uibase.handler.StateUiHandler
import com.example.structure.uibase.handler.StateUiOwner
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment  : BaseFragment(R.layout.fragment_home), StateUiOwner {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel: HomeViewModel by viewModel()
    override val viewModels get() = StateUiHandler.ViewModels(viewModel)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        android.util.Log.e("TEST_DATA","HomeFragment onViewCreated")
        bindView()
        observer()
        viewModel.getHomeMenu(BuildConfig.API_KEY)
    }

    private fun bindView() = with(binding) {

    }

    private fun observer() = with(viewModel) {
        homeMenus.observe(viewLifecycleOwner) {
            android.util.Log.e("TEST_DATA", "homeMenus= $homeMenus")
        }
    }
}
