package com.brighterbrain.project0.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import com.brighterbrain.project0.MainApplication
import com.brighterbrain.project0.di.component.ActivityComponent
import com.brighterbrain.project0.di.component.DaggerActivityComponent
import com.brighterbrain.project0.di.module.ActivityModule

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        ButterKnife.bind(this)
    }

    abstract fun getContentView(): Int

    fun getComponent(): ActivityComponent {
        return DaggerActivityComponent.builder()
                .applicationComponent(
                MainApplication.get(this).applicationComponent
        ).build()
    }
}