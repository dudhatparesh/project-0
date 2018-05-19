package com.birghterbrain.sellit.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.birghterbrain.sellit.di.ApplicationContext
import com.birghterbrain.sellit.di.DatabaseInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseHelper
    @Inject constructor(@ApplicationContext context: Context,
                        @DatabaseInfo databaseName: String,
                        @DatabaseInfo databaseVersion: Int)
    : SQLiteOpenHelper(context, databaseName, null, databaseVersion) {

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}