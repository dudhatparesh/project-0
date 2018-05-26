package com.brighterbrain.project0.data.network.response

import com.brighterbrain.project0.data.model.Item
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetItems: BaseResponse() {

    @SerializedName("data")
    @Expose
    var items: List<Item> = arrayListOf()
}