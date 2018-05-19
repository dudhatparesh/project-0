package com.birghterbrain.project0.ui.main

import android.view.View
import com.birghterbrain.project0.data.DataManager
import com.birghterbrain.project0.ui.base.BasePresenter

import javax.inject.Inject

class MainPresenter @Inject constructor(var dataManager: DataManager)
    : BasePresenter<MainMvpView>() {

    override fun attachView(view: MainMvpView) {
        super.attachView(view)
    }

    override fun detachView() {
        super.detachView()
    }

    fun fabClicked(view: View, message: String){
        if (isViewAttached()) {
            this.view?.displaySnackbar(view, message)
        }
    }
}