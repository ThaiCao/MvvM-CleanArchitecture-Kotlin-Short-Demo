package com.example.structure.uibase.widget

interface StatefulComponent {

    fun setState(state: State)

    fun getState(): State
}
