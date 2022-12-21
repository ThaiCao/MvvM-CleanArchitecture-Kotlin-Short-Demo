package com.example.structure.uibase.extend

import android.graphics.Paint
import android.widget.TextView

fun TextView.underline() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}
