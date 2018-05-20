package com.brighterbrain.project0.ui.main

import com.brighterbrain.project0.data.DataManager
import com.brighterbrain.project0.ui.base.BasePresenter
import com.brighterbrain.project0.ui.main.additem.AddItemFragment
import com.brighterbrain.project0.ui.main.viewitems.ViewItemsFragment
import io.reactivex.disposables.CompositeDisposable

import javax.inject.Inject

class MainPresenter @Inject constructor(var dataManager: DataManager)
    : BasePresenter<MainMvpView>() {

    var disposable: CompositeDisposable = CompositeDisposable()



    fun displayFragment(fragmentType: Int, addToBackstack:Boolean = true) {
        when(fragmentType){
            MainActivity.FRAGMENT_LIST_ITEMS->view?.displayFragment(ViewItemsFragment(),addToBackstack)
            MainActivity.FRAGMENT_ADD_ITEM->view?.displayFragment(AddItemFragment(),addToBackstack)
        }
    }
}