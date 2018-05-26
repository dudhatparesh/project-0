package com.brighterbrain.project0.data.network.response

import com.brighterbrain.project0.data.model.Item
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UpdateItemResponse : BaseResponse() {
    @SerializedName("data")
    @Expose
    val item: Item? = null
}

