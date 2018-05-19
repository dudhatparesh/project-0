package com.birghterbrain.sellit.di.component

import com.birghterbrain.sellit.MainActivity
import com.birghterbrain.sellit.di.PerActivity
import com.birghterbrain.sellit.di.module.ActivityModule
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class],
        modules = [ActivityModule::class])
interface ActivityComponent{
    fun inject(mainActivity: MainActivity)
}