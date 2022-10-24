package com.example.structure.presentation.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

fun <T, V> LiveData<T>.onNext(accept: MutableLiveData<V>.(T) -> Unit): LiveData<V> {
    return onNextNullable { accept(it!!) }
}

fun <T, V> LiveData<T>.onNextNullable(accept: MutableLiveData<V>.(T?) -> Unit): LiveData<V> {
    val next = MediatorLiveData<V>()
    next.addSource(this) {
        next.accept(it)
    }
    return next
}

operator fun <X, Y> LiveData<X>.plus(source: LiveData<Y>): LiveData<Pair<X, Y>> {
    val result = MediatorLiveData<Pair<X, Y>>()
    val activator = Activator<X, Y>()

    activator.onActivatedChange = {
        result.value = it
    }

    result.addSource(this) {
        activator.activeFirst(it)
    }

    result.addSource(source) {
        activator.activeSecond(it)
    }
    return result
}

private class Activator<T, V> {
    private var mSecondValue: V? = null
    private var mFirstValue: T? = null
    private var mFirstActivated: Boolean = false
    private var mSecondActivated: Boolean = false
    lateinit var onActivatedChange: (Pair<T, V>) -> Unit

    fun activeFirst(it: T?) {
        mFirstActivated = true
        mFirstValue = it
        notifyChangeIfNeeded()
    }

    fun activeSecond(it: V?) {
        mSecondActivated = true
        mSecondValue = it
        notifyChangeIfNeeded()
    }

    @Suppress("unchecked_cast")
    private fun notifyChangeIfNeeded() {
        if (mSecondActivated && mFirstActivated) onActivatedChange((mFirstValue to mSecondValue) as Pair<T, V>)
    }
}

fun <T> MutableLiveData<T>.refresh() {
    value = value
}
