package com.example.structure.feature.otp

import android.os.Bundle
import com.example.structure.R
import com.example.structure.common.fragmentparams.FragmentParams
import com.example.structure.model.presentation.LoginBackState
import com.example.structure.uibase.fragment.BaseFragment
import com.example.structure.uibase.handler.StateUiOwner
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthOtpParams(
    val loginBackState: LoginBackState,
    val isRegistration: Boolean
) : FragmentParams

class OtpFragment: BaseFragment(R.layout.fragment_otp), StateUiOwner {

    companion object {

        fun bundle(
            loginBackState: LoginBackState,
            isRegistration: Boolean = false
        ): Bundle {
            return AuthOtpParams(
                loginBackState = loginBackState,
                isRegistration = isRegistration
            ).toBundle()
        }
    }
}
