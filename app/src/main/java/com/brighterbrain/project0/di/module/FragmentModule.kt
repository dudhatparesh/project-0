package com.brighterbrain.project0.di.module

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import com.brighterbrain.project0.di.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(fragment: Fragment) {
    var mFragment: Fragment = fragment

    @Provides
    @ActivityContext
    fun provideContext(): Context? {
        return mFragment.context
    }

    @Provides
    fun provideActivity(): Activity? {
        return mFragment.activity
    }

    @Provides
    fun provideFragment(): Fragment {
        return mFragment
    }
}