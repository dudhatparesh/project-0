package com.brighterbrain.project0.ui.main.saveitem

import android.app.Activity
import android.location.Location
import android.os.Handler
import android.os.Looper
import com.brighterbrain.project0.R
import com.brighterbrain.project0.data.DataManager
import com.brighterbrain.project0.data.model.Item
import com.brighterbrain.project0.ui.base.BasePresenter
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SaveItemPresenter @Inject constructor(var dataManager: DataManager) : BasePresenter<SaveItemView>() {
    var compositeDisposable = CompositeDisposable()

    fun addItem(itemName: String, itemDesc: String, amount: String, currency: String, photoPath: String,
                lastLocation: Location?, itemId: Long?) {
        val addItemObserver: CompletableObserver = object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onComplete() {
                view?.displayMessage("Item Saved")
                Handler(Looper.getMainLooper()).postDelayed({
                    view?.popBack()
                },1000)
            }

            override fun onError(e: Throwable) {
                view?.displayMessage(e.localizedMessage)
            }
        }

        dataManager.saveItem(Item(name = itemName, description = itemDesc, amount = amount.toDouble(),
                latitude = lastLocation?.latitude,
                longitude = lastLocation?.longitude,
                currency = currency, id = itemId), photoPath)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addItemObserver)
    }

    fun checkPermissions(activity: Activity, requestCode: Int, perms: Array<String>) {

        if (dataManager.hasPermissions(perms)) {
            when (requestCode) {
                SaveItemFragment.RC_CAMERA -> view?.captureImage()
                SaveItemFragment.RC_LOCATION -> view?.checkLocationSettings()
                SaveItemFragment.RC_READ_SD_CARD -> view?.saveItem()
            }
        } else {

            if (dataManager.shouldAskPermission(perms, activity)) {
                view?.requestPermission(
                        activity.getString(R.string.to_create_gif_we_need_this_permission)
                        , requestCode, perms)
                dataManager.setPermissionAskedFirstTime(
                        perms, false)

            } else {
                view?.displayPermissionAlertDialog()
            }
        }

    }

    fun fillData(itemId: Long) {
        val getItemObserver: SingleObserver<Item> = object : SingleObserver<Item> {
            override fun onSuccess(item: Item) {
                view?.fillData(item)
            }

            override fun onSubscribe(disposable: Disposable) {
                compositeDisposable.add(disposable)
            }

            override fun onError(e: Throwable) {
                view?.displayMessage(e.localizedMessage)
            }
        }
        dataManager.getItem(itemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getItemObserver)
    }


}