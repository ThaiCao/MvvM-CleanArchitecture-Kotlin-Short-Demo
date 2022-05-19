package com.example.mydemo.remote.response

data class ApiResponse<T>(
    var data: T? = null,
    var error: Any? = null,
    var status: Boolean? = null
)
