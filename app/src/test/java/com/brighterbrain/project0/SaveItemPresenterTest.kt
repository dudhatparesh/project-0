package com.brighterbrain.project0

import android.app.Activity
import com.brighterbrain.project0.data.DataManager
import com.brighterbrain.project0.ui.main.saveitem.SaveItemFragment
import com.brighterbrain.project0.ui.main.saveitem.SaveItemFragment.Companion.RC_LOCATION
import com.brighterbrain.project0.ui.main.saveitem.SaveItemPresenter
import com.brighterbrain.project0.ui.main.saveitem.SaveItemView
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
class SaveItemPresenterTest {
    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()

    @Rule
    @JvmField
    val testRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var saveItemView: SaveItemView

    @Mock
    lateinit var dataManager: DataManager

    lateinit var saveItemPresenter: SaveItemPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        saveItemPresenter = SaveItemPresenter(dataManager)
        saveItemPresenter.attachView(saveItemView)
    }

    @Test
    fun testAddItemSuccess(){
        `when`(dataManager.saveItem(any(),"")).thenReturn(Completable.complete())
        saveItemPresenter.addItem("","","5.0","INR","",
                null)
        verify(saveItemView).displayMessage("Item Added")
        verify(saveItemView).popBack()
    }


    @Test
    fun testAddItemError(){
        `when`(dataManager.saveItem(any(),"")).thenReturn(Completable.error(Error("Custom Error Message")))
        saveItemPresenter.addItem("","","5.0","INR","",
                null)
        verify(saveItemView).displayMessage("Custom Error Message")
    }

    @Test
    fun checkPermissionShouldShouldCallCaptureImage(){
        val perms= arrayOf<String>()
        val activity= Mockito.mock(Activity::class.java)
        `when`(dataManager.hasPermissions(perms)).thenReturn(true)
        saveItemPresenter.checkPermissions(activity,SaveItemFragment.RC_CAMERA,perms)
        verify(saveItemView).captureImage()
    }


    @Test
    fun checkPermissionShouldShouldCallCheckLocation(){
        val perms= arrayOf<String>()
        val activity= Mockito.mock(Activity::class.java)
        `when`(dataManager.hasPermissions(perms)).thenReturn(true)
        saveItemPresenter.checkPermissions(activity,SaveItemFragment.RC_LOCATION,perms)
        verify(saveItemView).checkLocationSettings()
    }


    @Test
    fun checkPermissionShouldRequestPermission(){
        val perms= arrayOf<String>()
        val activity= Mockito.mock(Activity::class.java)
        `when`(dataManager.hasPermissions(perms)).thenReturn(false)
        `when`(dataManager.shouldAskPermission(perms,activity)).thenReturn(true)
        saveItemPresenter.checkPermissions(activity,SaveItemFragment.RC_LOCATION,perms)
        verify(saveItemView).requestPermission(activity.getString(R.string.to_create_gif_we_need_this_permission),
                RC_LOCATION,perms)
    }


    @Test
    fun checkPermissionShouldDisplayPermissionAlertDialog(){
        val perms= arrayOf<String>()
        val activity= Mockito.mock(Activity::class.java)
        `when`(dataManager.hasPermissions(perms)).thenReturn(false)
        `when`(dataManager.shouldAskPermission(perms,activity)).thenReturn(false)
        saveItemPresenter.checkPermissions(activity,SaveItemFragment.RC_LOCATION,perms)
        verify(saveItemView).displayPermissionAlertDialog()
    }
}