package com.example.mydemo.common.utils

import java.math.BigDecimal
import kotlin.math.floor


fun Float?.safeFloor(isFloor: Boolean = false, default: Float = 0f): Float {
    val value = this.safe(default)
    return if (isFloor) {
        floor(value)
    } else value
}

fun Double?.halfSafeFloor(isFloor: Boolean = false, default: Double = 0.0): Double {
    val value = this.safe(default) / 2
    return if (isFloor) {
        floor(value)
    } else value
}

fun Int?.safe(default: Int = 0): Int {
    return this ?: default
}

fun Long?.safe(default: Long = 0): Long {
    return this ?: default
}

fun Double?.safe(default: Double = 0.0): Double {
    return this ?: default
}

fun Float?.safe(default: Float = 0f): Float {
    return this ?: default
}

fun Boolean?.safe(default: Boolean = false): Boolean {
    return this ?: default
}

fun BigDecimal?.safe(default: BigDecimal = BigDecimal.ZERO): BigDecimal {
    return this ?: default
}

fun BigDecimal?.greaterThan(value: BigDecimal, onGreaterThan: (BigDecimal) -> Unit) {
    if (this == null) return
    if (this > value) {
        onGreaterThan(this)
    }
}

fun Double?.greaterThan(value: Double, onGreaterThan: (Double) -> Unit) {
    if (this == null) return
    if (this > value) {
        onGreaterThan(this)
    }
}

fun Float?.greaterThan(value: Float, onGreaterThan: (Float) -> Unit) {
    if (this == null) return
    if (this > value) {
        onGreaterThan(this)
    }
}

fun Double?.toBigDecimal(default: Double = 0.0): BigDecimal {
    this ?: return BigDecimal(default)

    return BigDecimal.valueOf(this)
}
