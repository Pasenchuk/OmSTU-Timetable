package com.mney.wallet.di

import android.app.Activity
import android.support.v4.app.Fragment
import com.mney.wallet.app.MneyApp

/**
 * Created by Pasenchuk Victor on 03/06/2017
 */


val Activity.appModule: AppModule
    get() = (this.application as MneyApp).appModule

val Fragment.appModule: AppModule
    get() = (this.activity?.application as MneyApp).appModule
