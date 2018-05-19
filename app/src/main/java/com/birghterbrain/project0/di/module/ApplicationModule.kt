package com.birghterbrain.project0.di.module

import android.app.Application
import android.content.Context
import com.birghterbrain.project0.di.ApplicationContext
import com.birghterbrain.project0.di.DatabaseInfo
import dagger.Module
import dagger.Provides
import javax.inject.Named

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
    @Named("databaseName")
    fun provideDatabaseName(): String{
        return "main.db"
    }
    @Provides
    @Named("databaseVersion")
    fun provideDatabaseVersion():Int{
        return 1
    }
}