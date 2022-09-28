package com.example.structure.common.extend

const val EMPTY = ""
const val SPACE = " "
const val ENTER = "\n"
const val COMMA = ", "
const val PLUS = "+"

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

fun String?.toBearerToken(): String? {
    if (this == null) return this
    return "Bearer $this"
}
