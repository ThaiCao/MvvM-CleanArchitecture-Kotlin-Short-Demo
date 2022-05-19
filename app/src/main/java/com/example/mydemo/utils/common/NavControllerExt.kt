package com.example.mydemo.utils.common

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.mydemo.R

fun NavController?.safeNavigate(
    destinationId: Int,
    bundle: (() -> Bundle?)? = null,
    navOptions: (() -> NavOptions)? = null
) {
    this?.currentDestination?.getAction(destinationId)?.let {
        navigate(destinationId, bundle?.invoke(), navOptions?.invoke())
    }
}

fun NavController?.safeNavigate(
    destinationId: Int,
    bundleData: Bundle? = null,
) {
    safeNavigate(
        destinationId = destinationId,
        bundle = { bundleData }
    )
}

fun navOptions(
    @IdRes popUpTo: Int? = null,
    inclusive: Boolean = false,
    withAnim: Boolean = false
): NavOptions {
    return NavOptions.Builder().apply {
        if (popUpTo != null) {
            setPopUpTo(popUpTo, inclusive)
        }

        if (withAnim) {
            setEnterAnim(R.anim.slide_in_right)
            setExitAnim(R.anim.slide_out_left)
            setPopEnterAnim(R.anim.slide_in_left)
            setPopExitAnim(R.anim.slide_out_right)
        }
    }.build()
}

fun navOptionsBottomToTop(
    @IdRes popUpTo: Int? = null,
    inclusive: Boolean = false,
    withAnim: Boolean = false
): NavOptions {
    return NavOptions.Builder().apply {
        if (popUpTo != null) {
            setPopUpTo(popUpTo, inclusive)
        }

        if (withAnim) {
            setEnterAnim(R.anim.slide_in_top)
            setExitAnim(R.anim.slide_out_bottom)
            setPopExitAnim(R.anim.slide_out_top)
        }
    }.build()
}