package com.example.mydemo.common.utils

const val EMPTY = ""
const val SPACE = " "
const val ENTER = "\n"
const val COMMA = ", "

fun String?.safe(default: String = ""): String {
    return this ?: default
}

fun String?.getOrNull(default: String? = null): String? {
    return if (this.isNullOrBlank()) default else this
}

fun String?.joinString(text: String?, separator: String = SPACE): String {
    if (this.isNullOrBlank()) return text.safe()
    if (text.isNullOrBlank()) return this.safe()
    return "$this$separator$text"
}

fun String?.isNotNullOrBlank(): Boolean = !this.isNullOrBlank()

fun CharSequence?.isNotNullOrBlank(): Boolean = this.toString().isNotNullOrBlank()

fun String?.toPosterPath(): String{
    return "https://image.tmdb.org/t/p/w500$this"
}