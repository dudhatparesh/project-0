package com.brighterbrain.project0.data

import android.content.Context
import com.brighterbrain.project0.data.model.Item
import com.brighterbrain.project0.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseHelper @Inject constructor(@ApplicationContext context: Context) {
    private var appDatabase: AppDatabase = AppDatabase.getInstance(context)!!

    fun saveItem(item: Item): Long{
        return appDatabase.itemDao().insert(item)
    }

    fun getAllItems(): List<Item> {
        return appDatabase.itemDao().getAllItems()
    }

}