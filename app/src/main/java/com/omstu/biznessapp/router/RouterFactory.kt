package com.omstu.biznessapp.router

import android.arch.lifecycle.MutableLiveData
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.omstu.biznessapp.router.RouterCommand
import com.omstu.biznessapp.router.impl.ActivityRouter
import com.omstu.biznessapp.router.impl.FragmentRouter

class RouterFactory {
    fun getRouter(activity: AppCompatActivity, liveData: MutableLiveData<MutableList<RouterCommand>>) = ActivityRouter(activity, liveData)
    fun getRouter(fragment: Fragment, liveData: MutableLiveData<MutableList<RouterCommand>>) = FragmentRouter(fragment, liveData)
}
