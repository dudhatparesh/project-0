package com.birghterbrain.project0.di.component

import com.birghterbrain.project0.ui.main.MainActivity
import com.birghterbrain.project0.di.PerActivity
import com.birghterbrain.project0.di.module.ActivityModule
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class],
        modules = [ActivityModule::class])
interface ActivityComponent{
    fun inject(mainActivity: MainActivity)
}