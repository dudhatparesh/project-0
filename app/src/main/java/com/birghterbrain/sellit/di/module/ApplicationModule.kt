package com.birghterbrain.sellit.di.module

import android.app.Application
import android.content.Context
import com.birghterbrain.sellit.di.ApplicationContext
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
    fun provideDatabaseName(): String{
        return "main.db"
    }
    @Provides
    fun provideDatabaseVersion():Int{
        return 1
    }
}