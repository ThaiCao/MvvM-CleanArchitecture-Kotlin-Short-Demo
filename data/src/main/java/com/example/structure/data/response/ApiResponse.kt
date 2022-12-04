package com.example.structure.data.response

data class ApiResponse<T>(
    var data: T? = null,
    var error: Any? = null,
    var status: Boolean? = null
)
