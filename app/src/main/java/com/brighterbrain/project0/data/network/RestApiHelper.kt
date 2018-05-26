package com.brighterbrain.project0.data.network

import com.brighterbrain.project0.data.model.Item
import com.brighterbrain.project0.data.network.response.BaseResponse
import com.brighterbrain.project0.data.network.response.GetItems
import com.brighterbrain.project0.data.network.response.SaveItemResponse
import com.brighterbrain.project0.utils.CommonUtils
import com.google.gson.GsonBuilder
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
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

    fun saveItem(item: Item, filePath: String): Call<SaveItemResponse> {
        val file = File(filePath)
        val reqFile = RequestBody.create(MediaType.parse("image/*"), file)
        val body = MultipartBody.Part.createFormData("file", file.name, reqFile)
        return if (item.id == null) {
            webServices.addItem(getRequestBody(item.name!!),
                    getRequestBody(item.description!!),
                    getRequestBody(item.amount!!.toString()),
                    getRequestBody(item.currency!!),
                    getRequestBody(item.latitude!!.toString()),
                    getRequestBody(item.longitude!!.toString()), body)
        } else {
            webServices.updateItem(getRequestBody(item.name!!),
                    getRequestBody(item.description!!),
                    getRequestBody(item.amount!!.toString()),
                    getRequestBody(item.currency!!),
                    getRequestBody(item.latitude!!.toString()),
                    getRequestBody(item.longitude!!.toString()),
                    getRequestBody(item.id!!.toString()), body)
        }
    }

    private fun getRequestBody(value: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), value)
    }
    fun deleteItem(id: Long):Call<BaseResponse>{
        return webServices.deleteItem(id)
    }
}
