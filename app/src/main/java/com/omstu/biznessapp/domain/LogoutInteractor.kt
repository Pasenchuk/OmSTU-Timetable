package com.mney.wallet.domain

import com.mney.wallet.R
import com.mney.wallet.repository.LocalRepository
import com.mney.wallet.router.RouterCommand
import com.mney.wallet.router.Screen
import com.mney.wallet.ui.base.BasePresenter
import com.mney.wallet.ui.base.BasePresenter.Companion.FLAG_CLEAR_BACK_STACK

class LogoutInteractor(val localRepository: LocalRepository) {
    fun logout(basePresenter: BasePresenter) {
        localRepository.id = ""
        localRepository.token = ""
        localRepository.selectedCoin = null

        basePresenter.postRouterCommandQueue(   RouterCommand.ShowToastRes(R.string.logged_out), RouterCommand.OpenScreen(Screen.MAIN, FLAG_CLEAR_BACK_STACK))
    }
}