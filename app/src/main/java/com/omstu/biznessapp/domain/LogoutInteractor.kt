package com.omstu.biznessapp.domain

import com.omstu.biznessapp.R
import com.omstu.biznessapp.repository.LocalRepository
import com.omstu.biznessapp.router.RouterCommand
import com.omstu.biznessapp.router.Screen
import com.omstu.biznessapp.ui.base.BasePresenter
import com.omstu.biznessapp.ui.base.BasePresenter.Companion.FLAG_CLEAR_BACK_STACK

class LogoutInteractor(val localRepository: LocalRepository) {
    fun logout(basePresenter: BasePresenter) {
        localRepository.fnpp = ""

        basePresenter.postRouterCommandQueue(RouterCommand.ShowToastRes(R.string.logged_out), RouterCommand.OpenScreen(Screen.MAIN, FLAG_CLEAR_BACK_STACK))
    }
}