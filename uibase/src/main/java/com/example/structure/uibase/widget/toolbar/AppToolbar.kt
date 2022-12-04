package com.example.structure.uibase.widget.toolbar

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.structure.uibase.R
import com.example.structure.uibase.widget.toolbar.state.ToolbarState
import kotlin.reflect.KClass

class AppToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var mDefStateClazz: String = ""
    private val manager = ToolbarStateManager(this)
    var factory: ToolbarStateFactory = ToolbarStateFactory.NewInstance

    init {
        with(context.obtainStyledAttributes(attrs, R.styleable.AppToolbar, defStyleAttr, 0)) {
            mDefStateClazz = getString(R.styleable.AppToolbar_stateName) ?: ""
            recycle()
        }
        if (mDefStateClazz.isNotBlank()) apply(manager.getStateClass(mDefStateClazz))
    }

    fun <T : ToolbarState> apply(state: KClass<T>, doApply: T.() -> Unit = {}) {
        manager.setState(state, factory, doApply)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        manager.clear()
    }

    fun getState(): ToolbarState {
        return manager.getToolbarState()
    }
}
