package com.brighterbrain.project0.data.network

import com.brighterbrain.project0.data.network.response.GetItems
import com.brighterbrain.project0.utils.CommonUtils
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestApiHelper @Inject constructor() {
    private val retrofit = Retrofit.Builder()
            .baseUrl(CommonUtils._BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation().create()))
            .build()!!
    private val webServices = retrofit.create(WebServices::class.java)!!
    fun getItems(): Call<GetItems> {
        return webServices.getItems()
    }
}
