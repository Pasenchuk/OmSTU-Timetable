package com.omstu.biznessapp.ui.main

import android.os.Bundle
import com.omstu.biznessapp.R
import com.omstu.biznessapp.di.AppModule
import com.omstu.biznessapp.ui.base.BaseActivity
import com.omstu.biznessapp.ui.base.BasePresenter

class MainActivity : BaseActivity() {

    private lateinit var mainPresenter: MainPresenter

    override fun initPresenter(appModule: AppModule): BasePresenter = MainPresenter(appModule)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = getPresenter(MainPresenter::class.java)
    }
}
