package com.example.structure.uibase.extend

import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.structure.uibase.bottomsheet.BaseBottomSheetDialogFragment
import com.example.structure.uibase.dialogfragment.BaseDialogFragment
import com.example.structure.uibase.fragment.BaseFragment
import kotlin.reflect.KClass


private const val KEY_REQUEST = "KEY_REQUEST"
private const val KEY_REFRESH = "KEY_REFRESH"
private const val KEY_DATA = "KEY_DATA"

fun <T : Parcelable> BaseFragment.setResult(data: T) {
    this.setFragmentResult(
        this::class.java.name + KEY_REQUEST,
        bundleOf(this::class.java.name + KEY_DATA to data)
    )
}

fun <T : Parcelable> BaseFragment.onResult(kClass: KClass<*>, func: (T) -> Unit) {
    this.setFragmentResultListener(kClass.java.name + KEY_REQUEST) { _, bundle ->
        bundle.getParcelable<T>(kClass.java.name + KEY_DATA)?.let {
            func.invoke(it)
        }
    }
}

fun BaseFragment.onRefresh(func: () -> Unit) {
    this.setFragmentResultListener(this::class.java.name + KEY_REFRESH) { _, _ ->
        func.invoke()
    }
}

fun BaseFragment.setRefresh(target: KClass<*>) {
    this.setFragmentResult(
        target.java.name + KEY_REFRESH,
        bundleOf()
    )
}

fun BaseDialogFragment.setRefresh(target: KClass<*>) {
    this.setFragmentResult(
        target.java.name + KEY_REFRESH,
        bundleOf()
    )
}

fun BaseBottomSheetDialogFragment.setRefresh(target: KClass<*>) {
    this.setFragmentResult(
        target.java.name + KEY_REFRESH,
        bundleOf()
    )
}
