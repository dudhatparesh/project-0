package com.brighterbrain.project0.ui.main

import android.support.v4.app.Fragment
import com.brighterbrain.project0.ui.base.MvpView

interface MainMvpView : MvpView {
    fun displayFragment(fragment: Fragment, addToBackstack: Boolean = true)
}