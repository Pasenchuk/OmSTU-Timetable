package com.omstu.biznessapp.di

import android.app.Activity
import android.support.v4.app.Fragment
import com.omstu.biznessapp.app.TimeTableApp

/**
 * Created by Pasenchuk Victor on 03/06/2017
 */


val Activity.appModule: AppModule
    get() = (this.application as TimeTableApp).appModule

val Fragment.appModule: AppModule
    get() = (this.activity?.application as TimeTableApp).appModule
