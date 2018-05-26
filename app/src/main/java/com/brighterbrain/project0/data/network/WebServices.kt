package com.brighterbrain.project0.data.network

import com.brighterbrain.project0.data.network.response.SaveItemResponse
import com.brighterbrain.project0.data.network.response.BaseResponse
import com.brighterbrain.project0.data.network.response.GetItems
import com.brighterbrain.project0.data.network.response.UpdateItemResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface WebServices {
    @GET("/items")
    fun getItems(): Call<GetItems>

    @POST("/item/add")
    @Multipart
    fun addItem(@Part("name") name: RequestBody,
                @Part("description") description: RequestBody,
                @Part("amount") amount: RequestBody,
                @Part("currency") currency: RequestBody,
                @Part("latitude") latitude: RequestBody,
                @Part("longitude") longitude: RequestBody,
                @Part imageFile: MultipartBody.Part): Call<SaveItemResponse>

    @POST("/items/update")
    fun updateItem(@Part("name") name: String,
                   @Part("description") description: String,
                   @Part("amount") amount: Double,
                   @Part("currency") currency: String,
                   @Part("latitude") latitude: Double,
                   @Part("longitude") longitude: Double,
                   @Part("id") id: Long,
                   @Part imageFile: MultipartBody.Part): Call<SaveItemResponse>

    @POST("/items/delete")
    fun deleteItem(): Call<BaseResponse>
}