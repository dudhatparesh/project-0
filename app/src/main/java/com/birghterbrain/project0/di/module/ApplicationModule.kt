package com.birghterbrain.project0.di.module

import android.app.Application
import android.content.Context
import com.birghterbrain.project0.di.ApplicationContext
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

}