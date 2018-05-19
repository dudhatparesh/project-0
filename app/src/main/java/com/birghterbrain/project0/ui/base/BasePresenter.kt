package com.birghterbrain.project0.ui.base

open class BasePresenter<T: MvpView> : Presenter<T>{
    var view: T? = null
    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun isViewAttached(): Boolean {
        return this.view !=null
    }



}