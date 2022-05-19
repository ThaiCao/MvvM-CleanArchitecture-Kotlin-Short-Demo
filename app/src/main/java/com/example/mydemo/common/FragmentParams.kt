package com.example.mydemo.common

import android.os.Bundle
import android.os.Parcelable

interface FragmentParams : Parcelable {
    fun toBundle(): Bundle {
        return Bundle().also {
            it.putParcelable(FRAGMENT_PARAMS, this)
        }
    }

    fun clear(bundle: Bundle?) {
        bundle?.remove(FRAGMENT_PARAMS)
    }

    companion object {
        private const val FRAGMENT_PARAMS = "fragment:params"

        fun <T : Parcelable> from(bundle: Bundle?): T {
            return bundle?.getParcelable(FRAGMENT_PARAMS) ?: error("Not found argument")
        }

        operator fun <T : Parcelable> get(bundle: Bundle?): T? {
            return bundle?.getParcelable(FRAGMENT_PARAMS)
        }

        operator fun <T : Parcelable> invoke(bundle: Bundle?): T {
            return bundle?.getParcelable(FRAGMENT_PARAMS) ?: error("Not found argument")
        }
    }
}
