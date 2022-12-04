package com.example.structure.uibase.widget.toolbar

import com.example.structure.uibase.widget.toolbar.state.NoToolbarState
import com.example.structure.uibase.widget.toolbar.state.ToolbarState
import kotlin.reflect.KClass

interface ToolbarStateFactory {
    fun create(kClass: KClass<out ToolbarState>): ToolbarState

    object NewInstance : ToolbarStateFactory {
        override fun create(kClass: KClass<out ToolbarState>): ToolbarState {
            if (kClass == NoToolbarState::class) return NoToolbarState
            return kClass.java.getConstructor().newInstance()
        }
    }
}
