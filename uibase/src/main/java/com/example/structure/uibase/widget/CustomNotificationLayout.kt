package com.example.structure.uibase.widget

import android.content.Context
import android.text.method.LinkMovementMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.structure.model.presentation.NotificationUi
import com.example.structure.uibase.R
import com.example.structure.uibase.extend.show
import com.example.structure.uibase.widget.image.CustomImageView

open class CustomNotificationLayout : LinearLayout, StatefulComponent {

    private var view: View? = null
    private var ivNotification: CustomImageView? = null
    private var llRoot: View? = null
    private var tvMessage: TextView? = null
    private var ivRemove: CustomImageView? = null

    private lateinit var currentState: State

    var onCloseClick: (() -> Unit)? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet? = null) {
        orientation = VERTICAL
        view = LayoutInflater.from(context).inflate(R.layout.view_notification_layout, null)
        ivNotification = view?.findViewById(R.id.ivNotification)
        llRoot = view?.findViewById(R.id.llRoot)
        tvMessage = view?.findViewById(R.id.tvMessage)
        ivRemove = view?.findViewById(R.id.iv_remove)
    }

    fun setMessage(notificationUi: NotificationUi?) {
        notificationUi ?: return
        val state = when (notificationUi) {
            is NotificationUi.Info -> InfoState(notificationUi.message)
            is NotificationUi.Alert -> AlertState(notificationUi.message)
            is NotificationUi.Success -> SuccessState(notificationUi.message)
        }
        setState(state)
    }

    override fun setState(state: State) {
        currentState = state
        currentState.apply()
    }

    override fun getState(): State = currentState

    open inner class InfoState(val message: CharSequence) : State {
        override fun apply(): Unit = with(view) {
            ivNotification?.setImageResource(R.drawable.ic_yellow_info)
            llRoot?.setBackgroundResource(R.drawable.bg_notification_info)
            tvMessage?.text = message
            tvMessage?.movementMethod = LinkMovementMethod.getInstance()

            ivRemove?.show(onCloseClick != null)
            ivRemove?.setOnClickListener { onCloseClick?.invoke() }
        }
    }

    open inner class SuccessState(val message: CharSequence) : State {
        override fun apply(): Unit = with(view) {
            ivNotification?.setImageResource(R.drawable.ic_green_check)
            llRoot?.setBackgroundResource(R.drawable.bg_notification_success)
            tvMessage?.text = message
            tvMessage?.movementMethod = LinkMovementMethod.getInstance()

            ivRemove?.show(onCloseClick != null)
            ivRemove?.setOnClickListener { onCloseClick?.invoke() }
        }
    }

    open inner class AlertState(val message: CharSequence) : State {
        override fun apply(): Unit = with(view) {
            ivNotification?.setImageResource(R.drawable.ic_alert)
            llRoot?.setBackgroundResource(R.drawable.bg_notification_alert)

            tvMessage?.text = message
            tvMessage?.movementMethod = LinkMovementMethod.getInstance()

            ivRemove?.show(onCloseClick != null)
            ivRemove?.setOnClickListener { onCloseClick?.invoke() }
        }
    }
}
