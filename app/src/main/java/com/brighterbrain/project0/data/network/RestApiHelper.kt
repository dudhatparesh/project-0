package com.brighterbrain.project0.data.network

import com.brighterbrain.project0.data.model.Item
import com.brighterbrain.project0.data.network.response.AddItemResponse
import com.brighterbrain.project0.data.network.response.GetItems
import com.brighterbrain.project0.utils.CommonUtils
import com.google.gson.GsonBuilder
import okhttp3.MediaType
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.RequestBody
import java.io.File


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
    fun addItem(item: Item, filePath:String): Call<AddItemResponse>{
        val file = File(filePath)
        val reqFile = RequestBody.create(MediaType.parse("image/*"), file)
        val body = MultipartBody.Part.createFormData("file", file.name, reqFile)
        return webServices.addItem(item.name!!,item.description!!,
                item.amount!! ,item.currency!! ,item.latitude!! ,
                item.longitude!! ,body )
    }
}
