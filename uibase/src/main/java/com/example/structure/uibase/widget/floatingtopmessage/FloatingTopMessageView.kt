package com.example.structure.uibase.widget.floatingtopmessage

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.structure.model.presentation.NotificationUi
import com.example.structure.uibase.R
import com.example.structure.uibase.widget.CustomNotificationLayout

class FloatingTopMessageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val DISMISS_TIMER = 3000L
        private const val SHOW_DELAY = 500L

        fun newInstance(fragment: Fragment): FloatingTopMessageView {
            return FloatingTopMessageView(fragment.requireContext()).apply {
                withLifecycle(fragment.viewLifecycleOwner)
            }
        }
    }

//    private val viewBinding: ViewTopMessageBinding =
//        ViewTopMessageBinding.inflate(LayoutInflater.from(context), this)
    val view: View = LayoutInflater.from(context).inflate(R.layout.view_top_message, null)
    val viewTopMessage: FloatTopMessageContainer? = view.findViewById(R.id.viewTopMessage)
    val vNotification: CustomNotificationLayout? = view.findViewById(R.id.v_notification)

    private val root: ViewGroup? = (context as? Activity)?.window?.decorView as? ViewGroup
    private var isShow = false

    private val dismissRunnable = {
        dismiss()
    }

    private fun withLifecycle(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                when (event) {
                    Lifecycle.Event.ON_DESTROY -> {
                        dismiss()
                    }
                    else -> {}
                }
            }
        })
    }

    fun show() {
        if (isShow) {
            return
        }
        isShow = true
        Handler(Looper.getMainLooper()).postDelayed(
            {
                root?.addView(this)
                doShow()
            },
            SHOW_DELAY
        )
    }

    private fun doShow() {
        delayToDismiss()
        viewTopMessage?.bounceSlideDownIfNeeded()
    }

    private fun delayToDismiss() {
        removeCallbacks(dismissRunnable)
        postDelayed(dismissRunnable, DISMISS_TIMER)
    }

    private fun dismiss() {
        if (!isShow) return
        isShow = false

        removeCallbacks(dismissRunnable)

        viewTopMessage?.bounceSlideUp {
            root?.removeView(this)
        }
    }

    fun setMessage(notificationUi: NotificationUi?): FloatingTopMessageView {
        notificationUi ?: return this
        vNotification?.setMessage(notificationUi)
        return this
    }
}
