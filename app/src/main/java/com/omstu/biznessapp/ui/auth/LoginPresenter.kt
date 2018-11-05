package com.omstu.biznessapp.ui.auth

import android.arch.lifecycle.MutableLiveData
import com.omstu.biznessapp.router.RouterCommand
import com.omstu.biznessapp.router.Screen
import com.omstu.biznessapp.ui.base.BasePresenter
import com.omstu.biznessapp.R
import com.omstu.biznessapp.di.AppModule

class LoginPresenter(appModule: AppModule) : BasePresenter(appModule) {

    val emailData = MutableLiveData<String>()

    init {
        emailData.postValue(localRepository.email)
    }

    fun onSignInClick(email: String, password: String) {

        when {
            password.isEmpty() -> postRouterCommandQueue(RouterCommand.ShowTextError(R.id.v_sign_in_password, R.string.should_not_be_empty))
            email.isEmpty() -> postRouterCommandQueue(RouterCommand.ShowTextError(R.id.v_sign_in_email, R.string.invalid_email))
            else -> {
                progressVisibility.postValue(true)

                appModule.authRepository
                        .login(email, password)
                        .compose(appModule.schedulersRepository.networkAsyncTransformer())
                        .subscribe({
                            progressVisibility.postValue(false)
                            if (it.err.isEmpty()) {
                                localRepository.email = email
                                localRepository.fnpp = it.fnpp
                                postRouterCommandQueue(RouterCommand.ShowToastRes(R.string.success), RouterCommand.OpenScreen(Screen.GROUPS, FLAG_CLEAR_BACK_STACK))
                            } else {
                                postRouterCommandQueue(RouterCommand.ShowTextError(R.id.v_sign_in_email, R.string.invalid_credentials),RouterCommand.ShowTextError(R.id.v_sign_in_password, R.string.invalid_credentials), RouterCommand.ShowToastStr(it.err))
                            }
                        }, errorHandler)
            }
        }

    }
}