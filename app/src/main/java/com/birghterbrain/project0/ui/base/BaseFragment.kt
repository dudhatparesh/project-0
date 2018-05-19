package com.birghterbrain.project0.ui.base

import android.support.v4.app.Fragment
import com.birghterbrain.project0.MainApplication
import com.birghterbrain.project0.di.component.DaggerFragmentComponent
import com.birghterbrain.project0.di.component.FragmentComponent

class BaseFragment:Fragment(){
    fun getComponent(): FragmentComponent{
        return  DaggerFragmentComponent.builder()
                .applicationComponent(MainApplication.get(context!!).applicationComponent)
                .build()
    }
}