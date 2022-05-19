package com.example.mydemo.features.dialog

import android.os.SystemClock
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class DialogFragmentLifecycleObserver<D : DialogFragment>(
    lifecycleOwner: LifecycleOwner,
    private val fm: FragmentManager,
    private val tag: String
) : LifecycleObserver {

    // variable to track event time
    private var mLastClickTime: Long = 0

    companion object {

        const val DEFAULT_THRESHOLD = 1000
    }

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    private var previousInstance: D? = null

    fun show(job: () -> D): D? {
        if (SystemClock.elapsedRealtime() - mLastClickTime < DEFAULT_THRESHOLD) {
            return previousInstance
        }
        mLastClickTime = SystemClock.elapsedRealtime()

        val fragment: D? = fm.findFragmentByTag(tag) as? D

        if (fragment != null) {
            previousInstance = fragment
        } else if (null == previousInstance) {
            previousInstance = job.invoke()
        }

        if (previousInstance?.isAdded == false) {
            previousInstance?.show(fm, tag)
        }

        return previousInstance
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        previousInstance?.apply {
            dismissAllowingStateLoss()
        }
        previousInstance = null
    }
}
