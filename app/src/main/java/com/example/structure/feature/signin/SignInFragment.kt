package com.example.structure.feature.signin

import android.os.Bundle
import android.view.View
import com.example.structure.R
import com.example.structure.common.fragmentparams.FragmentParams
import com.example.structure.databinding.FragmentSignInBinding
import com.example.structure.model.presentation.LoginBackState
import com.example.structure.presentation.feature.signin.SignInViewModel
import com.example.structure.uibase.extend.resetTranslucentStatusBar
import com.example.structure.uibase.extend.viewBinding
import com.example.structure.uibase.fragment.BaseFragment
import com.example.structure.uibase.handler.StateUiOwner
import kotlinx.parcelize.Parcelize
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@Parcelize
data class SignInParams(
    val loginBackState: LoginBackState,
) : FragmentParams

class SignInFragment: BaseFragment(R.layout.fragment_sign_in), StateUiOwner {
    private val signInNavigator: SignInNavigator by inject()
    private val binding by viewBinding(FragmentSignInBinding::bind)
    private val viewModel: SignInViewModel by viewModel()
    private var params: SignInParams? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.resetTranslucentStatusBar()
        params = FragmentParams[arguments]
        viewModel.with(params?.loginBackState)
        bindView()
    }

    private fun bindView() = with(binding) {
        btnLogin.setOnClickListener {
            signInNavigator.openAuthOtp((viewModel.loginBackState))
        }
        btnSignup.setOnClickListener {
            signInNavigator.gotoSignUp((viewModel.loginBackState))
        }
        btnForgotPass.setOnClickListener {
            signInNavigator.gotoForgotPassword()
        }
    }

//    private fun observer() = with(viewModel) {
//
//
//    }

    companion object {
        fun bundle(
            loginBackState: LoginBackState,
        ): Bundle {
            return SignInParams(
                loginBackState = loginBackState
            ).toBundle()
        }
    }
}
