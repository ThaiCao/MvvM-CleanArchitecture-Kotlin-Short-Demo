package com.example.structure.uibase.dialogfragment

import android.os.SystemClock
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class DialogFragmentLifecycleObserver<D : DialogFragment>(
    lifecycleOwner: LifecycleOwner,
    private val fm: FragmentManager,
    private val tag: String
) : LifecycleEventObserver {

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

    private fun onDestroy() {
        previousInstance?.apply {
            dismissAllowingStateLoss()
        }
        previousInstance = null
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_DESTROY -> {
                onDestroy()
            }
            else -> {}
        }
    }
}

