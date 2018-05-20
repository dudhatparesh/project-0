package com.brighterbrain.project0.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.Preference
import com.brighterbrain.project0.di.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(application: Application) {
    private var mApplication: Application = application

    @Provides
    @ApplicationContext
    fun provideContext(): Context{
        return mApplication
    }
    @Provides
    fun provideApplication(): Application{
        return mApplication
    }
    @Provides
    fun provideSharedPreferences(): SharedPreferences{
        return mApplication.getSharedPreferences("pref",Context.MODE_PRIVATE)
    }

}