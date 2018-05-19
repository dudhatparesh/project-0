package com.birghterbrain.project0.ui.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import butterknife.OnClick
import com.birghterbrain.project0.R
import com.birghterbrain.project0.ui.base.BaseActivity

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMvpView{
    @Inject
    lateinit var mainPresenter: MainPresenter

    companion object {
        val FRAGMENT_LIST_ITEMS = 0
        val FRAGMENT_ADD_ITEM = 1
        val FRAGMENT_ITEM_DETAIL = 2
    }
    override fun getContentView(): Int {
        return R.layout.activity_main
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        getComponent().inject(this)
        mainPresenter.attachView(this)
        mainPresenter.displayFragment(FRAGMENT_LIST_ITEMS)
    }

    override fun displayFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container,fragment)
                .addToBackStack("")
                .commit()
    }

}
