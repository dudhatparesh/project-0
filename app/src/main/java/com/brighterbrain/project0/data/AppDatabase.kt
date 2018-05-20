package com.brighterbrain.project0.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.brighterbrain.project0.data.model.Item
import com.brighterbrain.project0.di.ApplicationContext
import com.brighterbrain.project0.di.DatabaseInfo
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Database(entities = [Item::class],version = 1)
abstract class AppDatabase : RoomDatabase() {


    abstract fun itemDao(): ItemDao
    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context,
                            AppDatabase::class.java, "main.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}