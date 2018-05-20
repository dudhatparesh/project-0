package com.brighterbrain.project0.ui.base

interface Presenter<T:MvpView>{
    fun attachView(view:T)
    fun detachView()
    fun isViewAttached():Boolean
}