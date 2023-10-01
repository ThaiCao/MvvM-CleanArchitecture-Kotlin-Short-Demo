package com.example.structure.feature.detail

import com.example.structure.R
import com.example.structure.feature.signin.SignInFragment
import com.example.structure.navigation.BaseNavigator
import com.example.structure.navigation.BaseNavigatorImpl
import com.example.structure.presentation.model.LoginBackState
import com.example.structure.uibase.extend.navOptions
import com.example.structure.uibase.extend.safeNavigate

interface MovieDetailNavigator : BaseNavigator {
    fun openPlayer()

    fun openLogin()
}

class MovieDetailNavigatorImpl : BaseNavigatorImpl(), MovieDetailNavigator {
    override fun openPlayer() {
        requireNavigator().safeNavigate(
            destinationId = R.id.action_to_player,
            navOptions = {
                navOptions(
                    withAnim = true,
                    popUpTo = R.id.id_destination_player,
                    inclusive = true
                )
            }
        )
    }

    override fun openLogin() {
        requireNavigator().safeNavigate(
            destinationId = R.id.action_to_signin,
            bundle = {
                SignInFragment.bundle(loginBackState = LoginBackState.PlayerState)
            },
            navOptions = { navOptions(withAnim = true) }
        )
    }
}
