package com.example.structure.presentation.feature.signin

import com.example.structure.presentation.base.BaseViewModel
import com.example.structure.presentation.model.LoginBackState

class SignInViewModel : BaseViewModel(){

    var loginBackState: LoginBackState = LoginBackState.HomeState

    fun with(loginBackState: LoginBackState?) {
        this.loginBackState = loginBackState ?: LoginBackState.HomeState
    }
}
