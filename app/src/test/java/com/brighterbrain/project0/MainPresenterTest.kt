package com.brighterbrain.project0

import android.support.v4.app.Fragment
import com.brighterbrain.project0.ui.main.MainActivity
import com.brighterbrain.project0.ui.main.MainMvpView
import com.brighterbrain.project0.ui.main.MainPresenter
import com.brighterbrain.project0.ui.main.additem.AddItemFragment
import com.brighterbrain.project0.ui.main.viewitems.ViewItemsFragment
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatcher
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {
    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()


    @Mock
    lateinit var mainMvpView: MainMvpView


    lateinit var mainPresenter: MainPresenter
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainPresenter = MainPresenter()
        mainPresenter.attachView(mainMvpView)
    }

    @Test
    fun checkDisplayFragmentAddItem() {
        mainPresenter.displayFragment(MainActivity.FRAGMENT_ADD_ITEM, true)
        verify(mainMvpView).displayFragment(any<AddItemFragment>(), any())
    }


    @Test
    fun checkDisplayFragmentViewItems() {
        mainPresenter.displayFragment(MainActivity.FRAGMENT_LIST_ITEMS, true)
        verify(mainMvpView).displayFragment(any<ViewItemsFragment>(), any())
    }
}
