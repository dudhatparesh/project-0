package com.birghterbrain.project0.di.component

import com.birghterbrain.project0.di.PerFragment
import com.birghterbrain.project0.di.module.FragmentModule
import dagger.Component

@PerFragment
@Component(dependencies = [ApplicationComponent::class],
        modules = [FragmentModule::class])
interface FragmentComponent{

}