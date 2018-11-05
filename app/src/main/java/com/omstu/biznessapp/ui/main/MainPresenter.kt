package com.omstu.biznessapp.ui.main

import com.omstu.biznessapp.di.AppModule
import com.omstu.biznessapp.router.RouterCommand
import com.omstu.biznessapp.router.Screen
import com.omstu.biznessapp.ui.base.BasePresenter


class MainPresenter(appModule: AppModule) : BasePresenter(appModule) {
    init {
        postRouterCommandQueue(
                if (localRepository.fnpp.isNotEmpty())
                    RouterCommand.OpenScreen(Screen.GROUPS)
                else
                    RouterCommand.OpenScreen(Screen.LOGIN),
                RouterCommand.Close())
    }
}