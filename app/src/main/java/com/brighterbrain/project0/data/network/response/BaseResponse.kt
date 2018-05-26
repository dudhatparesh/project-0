package com.brighterbrain.project0.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @Expose
    @SerializedName("status")
    var status: Int? = null
    @Expose
    @SerializedName("message")
    var message: String? = null
}