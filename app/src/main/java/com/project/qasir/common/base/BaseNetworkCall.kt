package com.project.qasir.common.base

import com.squareup.moshi.Json

data class BaseResponse<T>(
    @Json(name = "status") val status: String,
    @Json(name = "message") val message: String,
    @Json(name = "data") val data: T
)