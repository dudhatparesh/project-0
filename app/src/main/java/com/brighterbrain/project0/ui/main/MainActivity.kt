package com.brighterbrain.project0.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import com.brighterbrain.project0.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMvpView {
    @Inject
    lateinit var mainPresenter: MainPresenter

    companion object {
        const val FRAGMENT_LIST_ITEMS = 0
        const val FRAGMENT_ADD_ITEM = 1
    }

    override fun getContentView(): Int {
        return R.layout.activity_main
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        getComponent().inject(this)
        mainPresenter.attachView(this)
        mainPresenter.displayFragment(FRAGMENT_LIST_ITEMS, false)
    }

    override fun displayFragment(fragment: Fragment, addToBackStack: Boolean) {

        val transaction = supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
        if (addToBackStack) {
            transaction.addToBackStack("")
        }
        transaction.commit()
    }

    override fun onDestroy() {
        mainPresenter.detachView()
        super.onDestroy()
    }

}
