package com.brighterbrain.project0.ui.main.viewitems

import com.brighterbrain.project0.data.DataManager
import com.brighterbrain.project0.data.model.Item
import com.brighterbrain.project0.data.network.response.GetItems
import com.brighterbrain.project0.ui.base.BasePresenter
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class ViewItemsPresenter
@Inject constructor(var dataManager: DataManager)
    : BasePresenter<ViewItemsMvpView>() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    fun loadItems() {
        val itemsObserver: SingleObserver<List<Item>> = object : SingleObserver<List<Item>> {
            override fun onSuccess(items: List<Item>) {
                view?.displayItems(items)
            }

            override fun onSubscribe(disposable: Disposable) {
                compositeDisposable.add(disposable)
            }

            override fun onError(e: Throwable) {
                view?.displayError(e.localizedMessage)
            }

        }
        dataManager.getItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(itemsObserver)
    }
    override fun detachView() {
        compositeDisposable.dispose()
        super.detachView()
    }
}