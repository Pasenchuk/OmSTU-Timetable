package com.mney.wallet.ui.auth

import android.arch.lifecycle.MutableLiveData
import com.mney.wallet.R
import com.mney.wallet.di.AppModule
import com.mney.wallet.router.RouterCommand
import com.mney.wallet.router.Screen
import com.mney.wallet.ui.base.BasePresenter
import com.mney.wallet.utils.md5

class LoginPresenter(appModule: AppModule) : BasePresenter(appModule) {

    val emailData = MutableLiveData<String>()

    init {
        emailData.postValue(localRepository.email)
    }

    fun onSignInClick(email: String, password: String) {

        when {
            password.isEmpty() -> postRouterCommandQueue(RouterCommand.ShowTextError(R.id.v_sign_in_password, R.string.should_not_be_empty))
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> postRouterCommandQueue(RouterCommand.ShowTextError(R.id.v_sign_in_email, R.string.invalid_email))
            else -> {
                progressVisibility.postValue(true)

                appModule.networkRepository
                        .login(email, md5(password))
                        .compose(appModule.schedulersRepository.networkAsyncTransformer())
                        .subscribe({
                            progressVisibility.postValue(false)
                            localRepository.email = email
                            localRepository.id = it.uID
                            postRouterCommandQueue(RouterCommand.ShowToastRes(R.string.success), RouterCommand.OpenScreen(Screen.CODE_CONFIRM, FLAG_CLEAR_BACK_STACK))
                        }, errorHandler)
            }
        }

    }
}