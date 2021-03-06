package com.brighterbrain.project0.data

import android.arch.persistence.room.*
import com.brighterbrain.project0.data.model.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM item ORDER BY id DESC")
    fun getAllItems(): List<Item>

    @Insert
    fun insertAll(items: List<Item>)

    @Query("DELETE FROM item WHERE id=:id")
    fun deleteItem(id: Long)

    @Insert
    fun insert(item: Item): Long

    @Query("DELETE FROM item")
    fun deleteAll()

    @Update
    fun update(item: Item)

    @Query("SELECT * FROM item WHERE id = :id")
    fun getItem(id: Long):Item?
}