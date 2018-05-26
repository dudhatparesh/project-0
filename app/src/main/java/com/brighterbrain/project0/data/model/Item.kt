package com.brighterbrain.project0.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "item")
data class Item(
        @PrimaryKey()
        @Expose
        @SerializedName("id")
        var id: Long? =null,

        @ColumnInfo(name = "name")
        @Expose
        var name: String? = null,

        @ColumnInfo(name= "description")
        @Expose
        var description: String? = null,

        @ColumnInfo(name ="image_name")
        @Expose
        var imageName: String? = null,

        @ColumnInfo(name = "latitude")
        @Expose
        var latitude: Double? = null,

        @ColumnInfo(name = "longitude")
        @Expose
        var longitude: Double? = null,

        @ColumnInfo(name = "amount")
        @Expose
        var amount: Double? = null,

        @ColumnInfo(name = "currency")
        @Expose
        var currency: String? = null
)