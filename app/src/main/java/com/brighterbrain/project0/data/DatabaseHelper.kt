package com.brighterbrain.project0.data

import android.content.Context
import com.brighterbrain.project0.data.model.Item
import com.brighterbrain.project0.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseHelper @Inject constructor(@ApplicationContext context: Context) {
    private var appDatabase: AppDatabase = AppDatabase.getInstance(context)!!

    fun addItem(item: Item): Long {
        return appDatabase.itemDao().insert(item)
    }

    fun saveItems(items: List<Item>) {
        appDatabase.itemDao().insertAll(items)
    }

    fun getAllItems(): List<Item> {
        return appDatabase.itemDao().getAllItems()
    }

    private fun deleteAll() {
        appDatabase.itemDao().deleteAll()
    }

    fun refillData(items: List<Item>) {
        appDatabase.runInTransaction {
            deleteAll()
            saveItems(items)
        }
    }

    fun updateItem(item: Item) {
        appDatabase.itemDao().update(item)
    }

    fun deleteItem(id: Long) {
        appDatabase.itemDao().deleteItem(id)
    }

    fun getItem(id: Long): Item? {
        return appDatabase.itemDao().getItem(id)
    }

}