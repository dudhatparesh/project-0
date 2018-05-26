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

    @POST("/item/update")
    @Multipart
    fun updateItem(@Part("name") name: RequestBody,
                   @Part("description") description: RequestBody,
                   @Part("amount") amount: RequestBody,
                   @Part("currency") currency: RequestBody,
                   @Part("latitude") latitude: RequestBody,
                   @Part("longitude") longitude: RequestBody,
                   @Part("id") id: RequestBody,
                   @Part imageFile: MultipartBody.Part): Call<SaveItemResponse>

    @POST("/item/delete")
    fun deleteItem(id:Long): Call<BaseResponse>
}