package com.example.structure.common.extend

fun <T> List<T>.replace(newValue: T, block: (T) -> Boolean): List<T> {
    return map {
        if (block(it)) newValue else it
    }
}

fun <T> List<T>.replaceByIndex(newValue: T?, position: Int): List<T> {
    return mapIndexed { index, item ->
        if (index == position) newValue ?: item else item
    }
}
