package com.brighterbrain.project0

import android.app.Activity
import android.location.Location
import com.brighterbrain.project0.data.DataManager
import com.brighterbrain.project0.ui.main.MainActivity
import com.brighterbrain.project0.ui.main.additem.AddItemFragment
import com.brighterbrain.project0.ui.main.additem.AddItemFragment.Companion.RC_LOCATION
import com.brighterbrain.project0.ui.main.additem.AddItemPresenter
import com.brighterbrain.project0.ui.main.additem.AddItemView
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Completable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AddItemPresenterTest {
    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()

    @Rule
    @JvmField
    val testRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var addItemView: AddItemView

    @Mock
    lateinit var dataManager: DataManager

    lateinit var addItemPresenter: AddItemPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        addItemPresenter = AddItemPresenter(dataManager)
        addItemPresenter.attachView(addItemView)
    }

    @Test
    fun testAddItemSuccess(){
        `when`(dataManager.addItem(any())).thenReturn(Completable.complete())
        addItemPresenter.addItem("","","5.0","INR","",
                null)
        verify(addItemView).displayMessage("Item Added")
        verify(addItemView).popBack()
    }


    @Test
    fun testAddItemError(){
        `when`(dataManager.addItem(any())).thenReturn(Completable.error(Error("Custom Error Message")))
        addItemPresenter.addItem("","","5.0","INR","",
                null)
        verify(addItemView).displayMessage("Custom Error Message")
    }

    @Test
    fun checkPermissionShouldShouldCallCaptureImage(){
        val perms= arrayOf<String>()
        val activity= Mockito.mock(Activity::class.java)
        `when`(dataManager.hasPermissions(perms)).thenReturn(true)
        addItemPresenter.checkPermissions(activity,AddItemFragment.RC_CAMERA,perms)
        verify(addItemView).captureImage()
    }


    @Test
    fun checkPermissionShouldShouldCallCheckLocation(){
        val perms= arrayOf<String>()
        val activity= Mockito.mock(Activity::class.java)
        `when`(dataManager.hasPermissions(perms)).thenReturn(true)
        addItemPresenter.checkPermissions(activity,AddItemFragment.RC_LOCATION,perms)
        verify(addItemView).checkLocationSettings()
    }


    @Test
    fun checkPermissionShouldRequestPermission(){
        val perms= arrayOf<String>()
        val activity= Mockito.mock(Activity::class.java)
        `when`(dataManager.hasPermissions(perms)).thenReturn(false)
        `when`(dataManager.shouldAskPermission(perms,activity)).thenReturn(true)
        addItemPresenter.checkPermissions(activity,AddItemFragment.RC_LOCATION,perms)
        verify(addItemView).requestPermission(activity.getString(R.string.to_create_gif_we_need_this_permission),
                RC_LOCATION,perms)
    }


    @Test
    fun checkPermissionShouldDisplayPermissionAlertDialog(){
        val perms= arrayOf<String>()
        val activity= Mockito.mock(Activity::class.java)
        `when`(dataManager.hasPermissions(perms)).thenReturn(false)
        `when`(dataManager.shouldAskPermission(perms,activity)).thenReturn(false)
        addItemPresenter.checkPermissions(activity,AddItemFragment.RC_LOCATION,perms)
        verify(addItemView).displayPermissionAlertDialog()
    }
}