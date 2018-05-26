package com.brighterbrain.project0.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.brighterbrain.project0.MainApplication
import com.brighterbrain.project0.data.DataManager
import com.brighterbrain.project0.data.AppDatabase
import com.brighterbrain.project0.data.DatabaseHelper
import com.brighterbrain.project0.data.network.RestApiHelper
import com.brighterbrain.project0.di.ApplicationContext
import com.brighterbrain.project0.di.module.ApplicationModule
import com.google.firebase.database.FirebaseDatabase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules= [(ApplicationModule::class)])
interface ApplicationComponent {
    fun inject(application:MainApplication)

    @ApplicationContext
    fun getContext():Context

    fun getApplication():Application

    fun getDataManager(): DataManager

    fun getDatabaseHelper(): DatabaseHelper

    fun getSharedPreferences(): SharedPreferences

    fun getFirebaseDatabase(): FirebaseDatabase

    fun getRestApiHelper(): RestApiHelper
}