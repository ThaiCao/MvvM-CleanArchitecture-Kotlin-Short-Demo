package com.example.structure.feature.signin

import android.os.Bundle
import com.example.structure.R
import com.example.structure.common.fragmentparams.FragmentParams
import com.example.structure.presentation.model.LoginBackState
import com.example.structure.uibase.fragment.BaseFragment
import com.example.structure.uibase.handler.StateUiOwner
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignInParams(
    val loginBackState: LoginBackState,
) : FragmentParams

class SignInFragment: BaseFragment(R.layout.fragment_sign_in), StateUiOwner {

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
