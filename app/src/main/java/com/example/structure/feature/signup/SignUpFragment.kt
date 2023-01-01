package com.example.structure.feature.signup

import android.os.Bundle
import com.example.structure.R
import com.example.structure.common.fragmentparams.FragmentParams
import com.example.structure.model.presentation.LoginBackState
import com.example.structure.uibase.fragment.BaseFragment
import com.example.structure.uibase.handler.StateUiOwner
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignupParams(
    val loginBackState: LoginBackState,
    val isNewUser: Boolean? = false
) : FragmentParams

class SignUpFragment: BaseFragment(R.layout.fragment_sign_up), StateUiOwner {

    companion object {

        fun bundle(
            loginBackState: LoginBackState,
            isNewUser: Boolean? = false
        ): Bundle {
            return SignupParams(
                loginBackState = loginBackState,
                isNewUser = isNewUser
            ).toBundle()
        }
    }
}
