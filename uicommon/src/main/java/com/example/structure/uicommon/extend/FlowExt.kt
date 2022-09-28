package com.example.structure.uicommon.extend

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Flow<T>.observe(
    scope: CoroutineScope,
    function: (T) -> Unit
) {
    scope.launch {
        collect {
            function(it)
        }
    }
}

fun <T> Flow<T>.observe(
    scope: LifecycleCoroutineScope,
    function: (T) -> Unit
) {
    scope.launchWhenStarted {
        collect {
            function(it)
        }
    }
}
