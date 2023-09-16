package com.example.structure.uibase.extend

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.structure.deeplink.DeepLink
import com.example.structure.R

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

fun NavController?.safeNavigateDeepLink(
    deepLink: DeepLink,
    navOptions: (() -> NavOptions)? = null
) {
    this ?: return
    if (currentDestination?.parent?.label != deepLink.graphLabel) {
        safeNavigate(
            destinationId = R.id.action_to_Main,
            navOptions = { navOptions(popUpTo = R.id.nav_graph) }
        )
    }

    currentDestination?.getAction(deepLink.destination)?.let {
        navigate(
            deepLink.destination,
            bundleOf(DeepLink.ARG_DEEP_LINK to deepLink),
            navOptions?.invoke()
        )
    }
}

fun NavController?.safeNavigateToMain() {
    this ?: return
    val mainGraphLabel = context.getString(R.string.label_main_graph_navigation)
    if (currentDestination?.parent?.label != mainGraphLabel) {
        safeNavigate(
            destinationId = R.id.action_to_Main,
            navOptions = { navOptions(popUpTo = R.id.nav_graph) }
        )
    }
}
