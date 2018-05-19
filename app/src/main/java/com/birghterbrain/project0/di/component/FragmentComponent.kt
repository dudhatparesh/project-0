package com.birghterbrain.project0.di.component

import com.birghterbrain.project0.di.PerFragment
import com.birghterbrain.project0.di.module.FragmentModule
import com.birghterbrain.project0.ui.main.additem.AddItemFragment
import com.birghterbrain.project0.ui.main.viewitems.ViewItemsFragment
import dagger.Component

@PerFragment
@Component(dependencies = [ApplicationComponent::class],
        modules = [FragmentModule::class])
interface FragmentComponent{
    fun inject(viewItemsFragment: ViewItemsFragment)
    fun inject(viewItemsFragment: AddItemFragment)

}