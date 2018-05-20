package com.brighterbrain.project0.ui.base

import android.support.v4.app.Fragment
import com.brighterbrain.project0.MainApplication
import com.brighterbrain.project0.di.component.DaggerFragmentComponent
import com.brighterbrain.project0.di.component.FragmentComponent

open class BaseFragment:Fragment(){
    fun getComponent(): FragmentComponent{
        return  DaggerFragmentComponent.builder()
                .applicationComponent(MainApplication.get(context!!).applicationComponent)
                .build()
    }
}