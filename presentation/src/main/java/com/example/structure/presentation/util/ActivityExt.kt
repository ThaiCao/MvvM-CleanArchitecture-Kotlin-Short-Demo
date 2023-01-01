package com.example.structure.presentation.util

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.example.structure.model.presentation.LatLng

fun Activity.openMapView(location: LatLng?) {
    val uri = Uri.parse("http://maps.google.com/maps")
        .buildUpon()
        .appendQueryParameter("q", "loc:${location?.latitude},${location?.longitude}")
    val intent = Intent(Intent.ACTION_VIEW, uri.build())
    startActivity(intent)
}
