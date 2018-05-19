package com.birghterbrain.project0.di.component

import android.app.Application
import android.content.Context
import com.birghterbrain.project0.MainApplication
import com.birghterbrain.project0.data.DataManager
import com.birghterbrain.project0.data.AppDatabase
import com.birghterbrain.project0.data.DatabaseHelper
import com.birghterbrain.project0.di.ApplicationContext
import com.birghterbrain.project0.di.module.ApplicationModule
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
}