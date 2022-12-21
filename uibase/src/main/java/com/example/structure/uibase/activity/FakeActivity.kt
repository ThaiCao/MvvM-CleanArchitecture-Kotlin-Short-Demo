package com.example.structure.uibase.activity

import android.os.Bundle
import android.os.Messenger
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.structure.common.message.MessengerHandler
import com.example.structure.common.message.MessengerHandler.sendResult

abstract class FakeActivity : AppCompatActivity() {

    private var messenger: Messenger? = null

    companion object {
        const val EXTRA_EXCEPTION = "extra_exception"
        const val EXTRA_PARAMS = "extra_params"
        const val EXTRA_IS_SUCCESS = "extra_is_success"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        messenger = MessengerHandler.getMessenger(intent)
    }

    private fun setMessengerResultBack(bundle: Bundle) {
        messenger?.sendResult(bundle)
    }

    private fun closeActivity() {
        finish()
    }

    protected fun setResultThrowAndFinish(throwable: Throwable) {
        setMessengerResultBack(bundleOf(EXTRA_EXCEPTION to throwable))
        closeActivity()
    }

    protected fun setResultSuccessAndFinish() {
        setMessengerResultBack(bundleOf(EXTRA_IS_SUCCESS to true))
        closeActivity()
    }

    protected fun setResultAndFinish(data : Bundle) {
        setMessengerResultBack(data)
        closeActivity()
    }

    override fun onDestroy() {
        super.onDestroy()
        messenger = null
    }
}
