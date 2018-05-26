package com.brighterbrain.project0.di.component

import com.brighterbrain.project0.di.PerFragment
import com.brighterbrain.project0.di.module.FragmentModule
import com.brighterbrain.project0.ui.main.saveitem.SaveItemFragment
import com.brighterbrain.project0.ui.main.viewitems.ViewItemsFragment
import dagger.Component

@PerFragment
@Component(dependencies = [ApplicationComponent::class],
        modules = [FragmentModule::class])
interface FragmentComponent{
    fun inject(viewItemsFragment: ViewItemsFragment)
    fun inject(viewItemsFragment: SaveItemFragment)

}