package com.example.structure.presentation.feature.signin

import com.example.structure.model.presentation.LoginBackState
import com.example.structure.presentation.base.BaseViewModel

class SignInViewModel : BaseViewModel() {

    var loginBackState: LoginBackState = LoginBackState.HomeState

    fun with(loginBackState: LoginBackState?) {
        this.loginBackState = loginBackState ?: LoginBackState.HomeState
    }
}
