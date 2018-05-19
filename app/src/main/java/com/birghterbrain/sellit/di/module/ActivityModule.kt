package com.birghterbrain.sellit.di.module

import android.app.Activity
import android.content.Context
import com.birghterbrain.sellit.di.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(activity: Activity) {
    private var mActivity: Activity = activity

    @Provides
    @ActivityContext
    fun provideContext(): Context {
        return mActivity
    }

    @Provides
    fun provideActivity(): Activity {
        return mActivity
    }
}