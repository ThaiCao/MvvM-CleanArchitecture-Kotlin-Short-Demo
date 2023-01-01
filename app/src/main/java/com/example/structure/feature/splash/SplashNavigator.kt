package com.example.structure.feature.splash

import com.example.structure.R
import com.example.structure.feature.signin.SignInFragment
import com.example.structure.model.presentation.LoginBackState
import com.example.structure.navigation.BaseNavigator
import com.example.structure.navigation.BaseNavigatorImpl
import com.example.structure.uibase.extend.navOptions
import com.example.structure.uibase.extend.safeNavigate

interface SplashNavigator : BaseNavigator {

    fun gotoMain()

    fun goToSignIn()
}

class SplashNavigatorImpl : BaseNavigatorImpl(), SplashNavigator {
    override fun gotoMain() {
        requireNavigator().safeNavigate(
            destinationId = R.id.action_to_Main,
//            navOptions = { navOptions(popUpTo = R.id.nav_graph) }
            navOptions = { navOptions(withAnim = true) }
        )
    }

    override fun goToSignIn() {
        requireNavigator().safeNavigate(
            destinationId = R.id.action_to_signin,
            bundle = {
                SignInFragment.bundle(loginBackState = LoginBackState.HomeState)
            },
            navOptions = { navOptions(withAnim = true) }
        )
    }
}

