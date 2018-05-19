package com.birghterbrain.sellit.di.component

import android.app.Application
import android.content.Context
import com.birghterbrain.sellit.MainApplication
import com.birghterbrain.sellit.di.ApplicationContext
import com.birghterbrain.sellit.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules= [(ApplicationModule::class)])
interface ApplicationComponent {
    fun inject(application:MainApplication)

    @ApplicationContext
    fun getContext():Context

    fun getApplication():Application
}