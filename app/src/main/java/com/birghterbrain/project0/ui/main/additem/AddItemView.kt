package com.birghterbrain.project0.ui.main.additem

import com.birghterbrain.project0.ui.base.MvpView

interface AddItemView: MvpView{
    fun displayMessage(message: String?)
    fun popBack()
}