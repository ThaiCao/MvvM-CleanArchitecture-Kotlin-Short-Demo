package com.example.structure.feature.signin

import com.example.structure.R
import com.example.structure.feature.otp.OtpFragment
import com.example.structure.feature.signup.SignUpFragment
import com.example.structure.model.presentation.LoginBackState
import com.example.structure.navigation.BaseNavigator
import com.example.structure.navigation.BaseNavigatorImpl
import com.example.structure.uibase.extend.navOptions
import com.example.structure.uibase.extend.safeNavigate

interface SignInNavigator : BaseNavigator {
    fun openAuthOtp(loginBackState: LoginBackState)

    fun gotoSignUp(loginBackState: LoginBackState)

    fun gotoForgotPassword()
}

class SignInNavigatorImpl : BaseNavigatorImpl(), SignInNavigator {
    override fun openAuthOtp(loginBackState: LoginBackState) {
        requireNavigator().safeNavigate(
            destinationId = R.id.action_to_auth_otp,
            bundle = {
                OtpFragment.bundle(
                    loginBackState = loginBackState,
                    isRegistration = false
                )
            },
            navOptions = { navOptions(withAnim = true) }
        )
    }

    override fun gotoSignUp(loginBackState: LoginBackState) {
        requireNavigator().safeNavigate(
            destinationId = R.id.action_to_signup,
            bundle = {
                SignUpFragment.bundle(
                    loginBackState = loginBackState
                )
            },
            navOptions = { navOptions(withAnim = true) }
        )
    }

    override fun gotoForgotPassword() {
        requireNavigator().safeNavigate(
            destinationId = R.id.action_to_forgot_password,
            navOptions = { navOptions(withAnim = true) }
        )
    }

}
