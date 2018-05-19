package com.birghterbrain.project0.ui.main

import android.os.Bundle
import android.support.design.widget.Snackbar
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
    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun displaySnackbar(view:View,message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        getComponent().inject(this)
        mainPresenter.attachView(this)
    }

    @OnClick(R.id.fab)
    fun fabClicked(view: View){
        mainPresenter.fabClicked(view,"Hello World")
    }

}
