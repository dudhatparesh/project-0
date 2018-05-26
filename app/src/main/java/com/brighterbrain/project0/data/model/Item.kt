package com.brighterbrain.project0.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "item")
data class Item(
        @PrimaryKey()
        var id: Long? =null,

        @ColumnInfo(name = "name")
        var name: String? = null,

        @ColumnInfo(name= "description")
        var description: String? = null,

        @ColumnInfo(name ="image_path")
        var imagePath: String? = null,

        @ColumnInfo(name = "latitude")
        var latitude: Double? = null,

        @ColumnInfo(name = "longitude")
        var longitude: Double? = null,

        @ColumnInfo(name = "amount")
        var amount: Double? = null,

        @ColumnInfo(name = "currency")
        var currency: String? = null
)