package com.example.structure.model.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


sealed class LoginBackState : Parcelable {
    @Parcelize
    object HomeState : LoginBackState() // go to Home screen

    @Parcelize
    object ProfileState : LoginBackState() // go to profile screen

    @Parcelize
    object PlayerState : LoginBackState() // go to player screen

}
