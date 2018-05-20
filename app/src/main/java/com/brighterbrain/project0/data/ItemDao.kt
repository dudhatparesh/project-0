package com.brighterbrain.project0.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.brighterbrain.project0.data.model.Item

@Dao
interface ItemDao{
    @Query("SELECT * FROM item ORDER BY id DESC")
    fun getAllItems(): List<Item>

    @Insert
    fun insertAll(items:List<Item>)

    @Delete
    fun deleteItem(item: Item)

    @Insert
    fun insert(item: Item):Long
}