package com.birghterbrain.project0.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.birghterbrain.project0.di.ApplicationContext
import com.birghterbrain.project0.di.DatabaseInfo
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class DatabaseHelper
    @Inject constructor(@ApplicationContext context: Context,
                        @Named("databaseName") databaseName: String,
                        @Named("databaseVersion") databaseVersion: Int)
    : SQLiteOpenHelper(context, databaseName, null, databaseVersion) {

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}