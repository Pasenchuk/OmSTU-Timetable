package com.omstu.biznessapp.ui.sign

import com.omstu.biznessapp.R
import com.omstu.biznessapp.di.AppModule
import com.omstu.biznessapp.router.RouterCommand
import com.omstu.biznessapp.router.Screen
import com.omstu.biznessapp.ui.base.BasePresenter


class SignPresenter(appModule: AppModule) : BasePresenter(appModule) {
    fun onFabClick(signInJSON: String) {
        postRouterCommandQueue(RouterCommand.ShowToastRes(R.string.sending), RouterCommand.Close())
    }
}