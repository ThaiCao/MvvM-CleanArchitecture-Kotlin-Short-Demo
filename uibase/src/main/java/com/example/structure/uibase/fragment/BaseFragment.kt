package com.example.structure.uibase.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.example.structure.uibase.handler.StateUiOwner

abstract class BaseFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addStateInterceptIfNeeded()
    }

    private fun addStateInterceptIfNeeded() {
        if (this is StateUiOwner) {
            viewModels.items.forEach {
                stateUiHandler.observe(this, it)
            }
        }
    }
}
