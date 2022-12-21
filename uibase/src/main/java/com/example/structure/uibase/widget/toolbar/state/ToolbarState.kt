package com.example.structure.uibase.widget.toolbar.state

import android.view.View

interface ToolbarState {
    val layoutId: Int

    fun onAttachView(view: View) {}
    fun onDetachView() {}
    fun onViewCreated(view: View) {}
}

interface SearchToolbarState : ToolbarState {
    fun showSearchIcon()
}

object NoToolbarState : ToolbarState {
    override val layoutId: Int = 0
}

interface SaveToolbarState : ToolbarState {

    fun updateSaveState(isEnable: Boolean)
}

