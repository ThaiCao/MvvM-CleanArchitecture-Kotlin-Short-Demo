package com.example.structure.common.message

import android.content.Intent
import android.os.*

object MessengerHandler {

    private const val EXTRA_MESSENGER = "extra_messenger"
    private const val EXTRA_MESSENGER_DATA = "extra_messenger_data"

    interface Callback {
        fun onResult(result: Bundle?)
    }

    fun Messenger.sendResult(value: Bundle?) {
        val message = Message.obtain()
        val bundle = Bundle()
        bundle.putParcelable(EXTRA_MESSENGER_DATA, value)
        message.data = bundle
        try {
            this.send(message)
        } catch (ex: Throwable) {
            ex.printStackTrace()
        }
    }

    fun getMessenger(intent: Intent): Messenger? {
        return intent.getParcelableExtra(EXTRA_MESSENGER)
    }

    private fun createMessenger(callback: Callback): Messenger {
        return Messenger(Handler(Looper.getMainLooper()) { msg ->
            val intent = msg.data.getParcelable<Bundle>(EXTRA_MESSENGER_DATA)
            callback.onResult(intent)
            true
        })
    }

    fun Intent.putMessenger(callback: Callback) {
        val messenger: Messenger = createMessenger(callback)
        this.putExtra(EXTRA_MESSENGER, messenger)
    }

}
