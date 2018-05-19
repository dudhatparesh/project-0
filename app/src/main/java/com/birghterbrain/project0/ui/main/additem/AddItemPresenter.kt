package com.birghterbrain.project0.ui.main.additem

import com.birghterbrain.project0.data.DataManager
import com.birghterbrain.project0.data.model.Item
import com.birghterbrain.project0.ui.base.BasePresenter
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddItemPresenter @Inject constructor(var dataManager: DataManager): BasePresenter<AddItemView>(){
    fun addItem(itemName: String, itemDesc: String, amount: String) {
        val addItemObserver: CompletableObserver = object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {
                disposable.add(d)
            }

            override fun onComplete() {
                view?.displayMessage("Item Added")
                view?.popBack()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                view?.displayMessage(e.localizedMessage)
            }
        }
        dataManager.addItem(Item(name = itemName,description = itemDesc,amount = amount.toDouble(),
                currency = "INR"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addItemObserver)
    }

    var disposable = CompositeDisposable()

}