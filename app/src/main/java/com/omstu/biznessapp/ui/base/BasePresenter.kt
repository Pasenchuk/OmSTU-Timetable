package com.mney.wallet.ui.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Intent
import com.mney.wallet.R
import com.mney.wallet.di.AppModule
import com.mney.wallet.repository.LocalRepository
import com.mney.wallet.repository.NetworkRepository
import com.mney.wallet.router.RouterCommand
import com.mney.wallet.router.Screen

abstract class BasePresenter(val appModule: AppModule) : ViewModel() {

    val progressVisibility = MutableLiveData<Boolean>()
    val routerCommandQueue = MutableLiveData<MutableList<RouterCommand>>()

    val localRepository: LocalRepository = appModule.localRepository
    val networkRepository: NetworkRepository = appModule.networkRepository

    val errorHandler: (Throwable) -> Unit = {
        progressVisibility.postValue(false)
        postRouterCommandQueue(RouterCommand.ShowToastRes(R.string.network_error))
    }

    fun postRouterCommandQueue(vararg commands: RouterCommand) {
        val list = mutableListOf<RouterCommand>()
        for (command in commands) {
            list.add(command)
        }
        routerCommandQueue.postValue(list)
    }

    fun onSettingsClick() {
        postRouterCommandQueue(RouterCommand.OpenScreen(Screen.SETTINGS))
    }

    companion object {
        const val FLAG_CLEAR_BACK_STACK = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
    }

}