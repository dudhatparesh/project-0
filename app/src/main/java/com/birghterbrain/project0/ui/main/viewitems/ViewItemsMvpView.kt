package com.birghterbrain.project0.ui.main.viewitems

import com.birghterbrain.project0.data.model.Item
import com.birghterbrain.project0.ui.base.MvpView

interface ViewItemsMvpView:MvpView{
    fun displayItems(items:List<Item>)
    fun displayError(errorMessage: String?)
}