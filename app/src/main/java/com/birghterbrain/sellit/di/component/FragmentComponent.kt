package com.birghterbrain.sellit.di.component

import com.birghterbrain.sellit.di.PerFragment
import com.birghterbrain.sellit.di.module.FragmentModule
import dagger.Component

@PerFragment
@Component(dependencies = [ApplicationComponent::class],
        modules = [FragmentModule::class])
interface FragmentComponent{

}