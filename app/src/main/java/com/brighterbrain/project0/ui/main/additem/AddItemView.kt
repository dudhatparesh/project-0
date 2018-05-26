package com.brighterbrain.project0.ui.main.additem

import com.brighterbrain.project0.ui.base.MvpView

interface AddItemView: MvpView{
    fun displayMessage(message: String?)
    fun popBack()
    fun requestPermission(rationaleText: String?, requestCode: Int, perms: Array<String>)
    fun displayPermissionAlertDialog()
    fun captureImage()
    fun getImageFromGallery()
    fun fetchLocation()
    fun checkLocationSettings()
    fun saveItem()
}