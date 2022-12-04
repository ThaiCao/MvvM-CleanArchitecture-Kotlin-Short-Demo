package com.example.structure.uibase.widget.toolbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.structure.uibase.widget.toolbar.state.NoToolbarState
import com.example.structure.uibase.widget.toolbar.state.ToolbarState
import kotlin.reflect.KClass

class ToolbarStateManager(private val viewContainer: ViewGroup) {

    private val inflater = LayoutInflater.from(viewContainer.context)
    private var mState: ToolbarState = NoToolbarState
    private val contentView get() = viewContainer.getChildAt(0)

    @Suppress("unchecked_cast")
    fun <T : ToolbarState> setState(
        stateClazz: KClass<T>,
        factory: ToolbarStateFactory,
        doApply: T.() -> Unit = {}
    ) {
        if (mState.javaClass == stateClazz.java) {
            reuseState(doApply)
            return
        }
        val state = factory.create(stateClazz)
        setState(state) { doApply(state as T) }
    }

    @Suppress("unchecked_cast")
    private fun <T> reuseState(doApply: T.() -> Unit) {
        doApply(mState as T)
        if (mState.layoutId == 0) gone()
        else {
            visibleIfNeeded()
            mState.onViewCreated(contentView)
        }
    }

    private fun setState(value: ToolbarState, doApply: () -> Unit = {}) {
        mState.onDetachView()
        viewContainer.removeAllViews()
        mState = value

        if (value.layoutId == 0) {
            gone()
            return
        }
        visibleIfNeeded()

        val contentView = inflater.inflate(value.layoutId, viewContainer, false)
        viewContainer.addView(contentView)
        attachView(contentView, value, doApply)
    }

    private fun visibleIfNeeded() {
        if (viewContainer.visibility != View.VISIBLE) viewContainer.visibility = View.VISIBLE
    }

    private fun gone() {
        viewContainer.visibility = View.GONE
    }

    private fun attachView(view: View, value: ToolbarState, doApply: () -> Unit) {
        value.onAttachView(view)
        doApply()
        value.onViewCreated(view)
    }

    fun clear() {
        mState.onDetachView()
        mState = NoToolbarState
    }

    fun getStateClass(stateClazz: String): KClass<out ToolbarState> {
        return try {
            Class.forName(stateClazz).asSubclass(ToolbarState::class.java).kotlin
        } catch (e: Throwable) {
            error("$stateClazz should be extend from ToolbarState")
        }
    }

    fun getToolbarState(): ToolbarState {
        return mState
    }
}
