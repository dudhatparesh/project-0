package com.brighterbrain.project0.ui.main.saveitem

import com.brighterbrain.project0.data.model.Item
import com.brighterbrain.project0.ui.base.MvpView

interface SaveItemView: MvpView{
    fun displayMessage(message: String?)
    fun popBack()
    fun requestPermission(rationaleText: String?, requestCode: Int, perms: Array<String>)
    fun displayPermissionAlertDialog()
    fun captureImage()
    fun getImageFromGallery()
    fun fetchLocation()
    fun checkLocationSettings()
    fun saveItem()
    fun fillData(item: Item)
    fun displayProgressDialog()
    fun hideProgressDialog()
}