package com.birghterbrain.project0.ui.main

import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View
import com.birghterbrain.project0.ui.base.BaseFragment
import com.birghterbrain.project0.ui.base.MvpView

interface MainMvpView:MvpView{
    fun displayFragment(fragment: Fragment)
}