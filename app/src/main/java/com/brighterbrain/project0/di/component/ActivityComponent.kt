package com.brighterbrain.project0.di.component

import com.brighterbrain.project0.di.PerActivity
import com.brighterbrain.project0.di.module.ActivityModule
import com.brighterbrain.project0.ui.main.MainActivity
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class],
        modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}