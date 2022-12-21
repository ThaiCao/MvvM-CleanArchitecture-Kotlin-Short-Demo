package com.example.structure.deeplink.model

import android.os.Parcelable
import com.example.structure.deeplink.R
import kotlinx.parcelize.Parcelize

sealed class DeepLink : Parcelable {

    abstract val destination: Int

    open val graphLabel: String = "MainNavGraph"

    @Parcelize
    data class Home(
        override val destination: Int = R.id.global_action_to_home
    ) : DeepLink()

    @Parcelize
    data class Detail(
        val detailId: String?,
        override val destination: Int = R.id.global_action_to_detail
    ) : DeepLink()

    @Parcelize
    data class Category(
        override val destination: Int = R.id.global_action_to_category
    ) : DeepLink()

    companion object {
        const val ARG_DEEP_LINK = "arg_deep_link"
    }
}

