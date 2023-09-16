package com.example.structure.uibase.extend

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

fun LifecycleOwner.onDestroy(function: () -> Unit): LifecycleOwner {
    lifecycle.addObserver(object : LifecycleEventObserver {

        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            when (event) {
                Lifecycle.Event.ON_DESTROY -> {
                    function()
                }
                else -> {}
            }
        }
    })
    return this
}

fun LifecycleOwner.onStop(function: () -> Unit): LifecycleOwner {
    lifecycle.addObserver(object : LifecycleEventObserver {

        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            when (event) {
                Lifecycle.Event.ON_STOP -> {
                    function()
                }
                else -> {}
            }
        }
    })
    return this
}
