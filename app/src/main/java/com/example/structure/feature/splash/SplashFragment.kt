package com.example.structure.feature.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import com.example.structure.R
import com.example.structure.databinding.FragmentSplashBinding
import com.example.structure.presentation.feature.splash.SplashViewModel
import com.example.structure.uibase.extend.translucentStatusBar
import com.example.structure.uibase.extend.viewBinding
import com.example.structure.uibase.fragment.BaseFragment
import com.example.structure.uibase.handler.StateUiHandler
import com.example.structure.uibase.handler.StateUiOwner
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment(R.layout.fragment_splash), StateUiOwner {

    private val binding by viewBinding(FragmentSplashBinding::bind)
    private val splashNavigator: SplashNavigator by inject()

    private val splashViewModel: SplashViewModel by viewModel()
    override val viewModels get() = StateUiHandler.ViewModels(splashViewModel)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.translucentStatusBar()

        setupView()
        observeData()
        splashViewModel.viewModelScope.launch {
            splashViewModel.init()
        }
    }

    private fun setupView()= with(binding)  {

    }

    private fun observeData() = with(splashViewModel) {
        goToMain.observe(viewLifecycleOwner) {
            splashNavigator.gotoMain()
        }
    }
}
