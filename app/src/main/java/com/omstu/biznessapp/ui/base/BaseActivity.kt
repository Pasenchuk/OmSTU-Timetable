package com.omstu.biznessapp.ui.base

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.omstu.biznessapp.di.AppModule
import com.omstu.biznessapp.di.appModule
import com.omstu.biznessapp.router.Router

abstract class BaseActivity : AppCompatActivity() {

    abstract fun initPresenter(appModule: AppModule): BasePresenter
    lateinit var router: Router
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
    }


    @Suppress("UNCHECKED_CAST")
    fun <T : BasePresenter> getPresenter(clazz: Class<T>): T = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return initPresenter(this@BaseActivity.appModule) as T
        }
    })[clazz].apply {
        router = appModule.routerFactory.getRouter(this@BaseActivity, routerCommandQueue)

        progressVisibility.observe(this@BaseActivity, Observer {
            if (it == true)
                progressDialog.show()
            else
                progressDialog.hide()
        })

    }

    infix fun Int.calls(cb: () -> Unit) = findViewById<View>(this).setOnClickListener { cb() }

}