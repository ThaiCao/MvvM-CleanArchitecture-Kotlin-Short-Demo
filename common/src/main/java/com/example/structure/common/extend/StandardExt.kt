package com.example.structure.common.extend

fun <T> block(item: T?, function: T.() -> Unit) {
    item?.apply(function)
}
