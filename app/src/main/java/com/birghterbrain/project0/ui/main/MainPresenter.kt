package com.birghterbrain.project0.ui.main

import android.view.View
import com.birghterbrain.project0.data.DataManager
import com.birghterbrain.project0.data.model.Item
import com.birghterbrain.project0.ui.base.BasePresenter
import com.birghterbrain.project0.ui.main.additem.AddItemFragment
import com.birghterbrain.project0.ui.main.viewitems.ViewItemsFragment
import io.reactivex.CompletableObserver
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class MainPresenter @Inject constructor(var dataManager: DataManager)
    : BasePresenter<MainMvpView>() {

    var disposable: CompositeDisposable = CompositeDisposable()



    fun displayFragment(fragmentType: Int) {
        when(fragmentType){
            MainActivity.FRAGMENT_LIST_ITEMS->view?.displayFragment(ViewItemsFragment())
            MainActivity.FRAGMENT_ADD_ITEM->view?.displayFragment(AddItemFragment())
        }
    }
}