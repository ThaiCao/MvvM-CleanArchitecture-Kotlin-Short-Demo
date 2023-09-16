package com.example.structure.uibase.extend

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.example.structure.deeplink.DeepLink
import java.io.Serializable

@Suppress("unchecked_cast")
inline fun <reified T> Fragment.argument(key: String? = null) = lazy(LazyThreadSafetyMode.NONE) {
    (arguments?.get(key ?: T::class.java.simpleName) as? T)
        ?: throw RuntimeException("Argument $key not found")
}

@Suppress("unchecked_cast")
inline fun <reified T : Any> Fragment.argumentNullable(key: String? = null): Lazy<T?> =
    lazy(LazyThreadSafetyMode.NONE) {
        (arguments?.get(key ?: T::class.java.simpleName) as? T)
    }

fun Serializable.toBundle(key: String? = null): Bundle {
    return Bundle().also {
        it.putSerializable(key ?: this.javaClass.simpleName, this)
    }
}

fun Parcelable.toBundle(key: String? = null): Bundle {
    return Bundle().also {
        it.putParcelable(key ?: this.javaClass.simpleName, this)
    }
}

inline fun <reified T : Any> Bundle?.get(): T? {
    return this?.get(T::class.java.name) as? T
}

inline fun <reified T : Any> Bundle?.get(key: String): T? {
    return this?.get(key) as? T
}

fun <T : DeepLink> Fragment.getDeepLink(function: (T) -> Unit) {
    arguments?.getParcelable<T>(DeepLink.ARG_DEEP_LINK)?.let {
        function(it)
    }
}

fun <T : DeepLink> Fragment.getDeepLinkData(): T? {
    return arguments?.getParcelable(DeepLink.ARG_DEEP_LINK)
}
