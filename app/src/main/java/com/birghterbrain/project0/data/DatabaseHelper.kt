package com.birghterbrain.project0.data

import android.content.Context
import com.birghterbrain.project0.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseHelper {
    lateinit var appDatabase: AppDatabase
    @Inject constructor(@ApplicationContext context: Context){
        appDatabase = AppDatabase.getInstance(context)!!
    }
}