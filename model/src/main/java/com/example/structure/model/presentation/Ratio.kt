package com.example.structure.model.presentation

data class Ratio(val width: Float, val height: Float) {
    companion object {
        const val DEFAULT_RATIO = -1f

        fun default(): Ratio {
            return Ratio(DEFAULT_RATIO, DEFAULT_RATIO)
        }
    }
}
