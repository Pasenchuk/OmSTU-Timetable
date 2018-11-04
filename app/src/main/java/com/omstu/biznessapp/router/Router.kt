package com.omstu.biznessapp.router

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

abstract class Router(activity: LifecycleOwner, liveData: MutableLiveData<MutableList<RouterCommand>>) {
    init {
        liveData.observe(activity, Observer<MutableList<RouterCommand>> {
            it?.let { commands ->
                while (commands.size > 0)
                    executeCommand(commands.removeAt(0))
                liveData.postValue(commands)
            }
        })
    }

    abstract fun executeCommand(command: RouterCommand)
}
