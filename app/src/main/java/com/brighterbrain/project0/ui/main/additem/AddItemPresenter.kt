package com.brighterbrain.project0.ui.main.additem

import android.app.Activity
import android.net.Uri
import com.brighterbrain.project0.R
import com.brighterbrain.project0.data.DataManager
import com.brighterbrain.project0.data.model.Item
import com.brighterbrain.project0.ui.base.BasePresenter
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddItemPresenter @Inject constructor(var dataManager: DataManager): BasePresenter<AddItemView>(){
    fun addItem(itemName: String, itemDesc: String, amount: String, currency: String, photoUri: String?) {
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
                imagePath = photoUri,
                currency = currency))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addItemObserver)
    }

        fun checkPermissions(activity:Activity, requestCode:Int, perms: Array<String>) {

            if (dataManager.hasPermissions( perms)) {
                when(requestCode){
                    AddItemFragment.RC_CAMERA -> view?.captureImage()
                }
            } else {

                if (dataManager.shouldAskPermission(perms,activity)) {
                    view?.requestPermission(
                            activity.getString(R.string.to_create_gif_we_need_this_permission)
                            ,requestCode,perms)
                    dataManager.setPermissionAskedFirstTime(
                            perms, false)

                } else {
                    view?.displayPermissionAlertDialog()
                }
            }

    }

    var disposable = CompositeDisposable()

}