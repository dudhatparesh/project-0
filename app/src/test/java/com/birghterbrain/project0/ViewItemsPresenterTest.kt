package com.birghterbrain.project0

import com.brighterbrain.project0.data.DataManager
import com.brighterbrain.project0.data.model.Item
import com.brighterbrain.project0.ui.main.viewitems.ViewItemsMvpView
import com.brighterbrain.project0.ui.main.viewitems.ViewItemsPresenter
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.annotations.NonNull
import io.reactivex.functions.Function
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.junit.runners.model.Statement
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Callable

@RunWith(MockitoJUnitRunner::class)
class ViewItemsPresenterTest {

    @Rule @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()
    @Mock
    lateinit var viewItemsMvpView: ViewItemsMvpView

    @Mock
    lateinit var dataManager: DataManager

    lateinit var viewItemsPresenter: ViewItemsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewItemsPresenter = ViewItemsPresenter(dataManager)
        viewItemsPresenter.attachView(viewItemsMvpView)
    }

    @Test
    fun checkValidData() {
        val data = (listOf(Item()))
        `when`(dataManager.getItems()).thenReturn(Single.just(data))
        viewItemsPresenter.loadItems()
        verify(viewItemsMvpView).displayItems(data)
    }

}