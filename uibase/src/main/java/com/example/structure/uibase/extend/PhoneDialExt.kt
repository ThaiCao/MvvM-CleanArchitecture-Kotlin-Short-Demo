package com.example.structure.uibase.extend

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat

fun Context.openPhoneDial(phone: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phone))
    if (intent.resolveActivity(this.packageManager) != null) {
        ContextCompat.startActivity(this, intent, null)
    }
}
