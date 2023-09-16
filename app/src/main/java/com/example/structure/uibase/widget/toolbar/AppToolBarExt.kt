package com.example.structure.uibase.widget.toolbar

import com.example.structure.uibase.widget.toolbar.state.ToolbarState


inline fun <reified T : ToolbarState> AppToolbar.apply(noinline doApply: T.() -> Unit = {}) {
    apply(T::class, doApply)
}
