package com.brighterbrain.project0.data.network

import com.brighterbrain.project0.data.network.response.AddItemResponse
import com.brighterbrain.project0.data.network.response.BaseResponse
import com.brighterbrain.project0.data.network.response.GetItems
import com.brighterbrain.project0.data.network.response.UpdateItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface WebServices {
    @GET("/items")
    fun getItems(): Call<GetItems>

    @POST("/items/add")
    fun addItem(): Call<AddItemResponse>

    @POST("/items/update")
    fun updateItem(): Call<UpdateItemResponse>

    @POST("/items/delete")
    fun deleteItem(): Call<BaseResponse>
}