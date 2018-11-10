package com.omstu.biznessapp.ui.table

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import com.omstu.biznessapp.R
import com.omstu.biznessapp.di.AppModule
import com.omstu.biznessapp.domain.GroupHeaderModel
import com.omstu.biznessapp.network.request.TableRequest
import com.omstu.biznessapp.network.response.StudentItem
import com.omstu.biznessapp.router.Button
import com.omstu.biznessapp.router.RouterCommand
import com.omstu.biznessapp.router.Screen
import com.omstu.biznessapp.ui.base.BasePresenter

class TablePresenter(appModule: AppModule) : BasePresenter(appModule) {

    val studentsData = MutableLiveData<List<StudentItem>>()

    var group: GroupHeaderModel? = null

    init {
        studentsData.postValue(listOf())
        localRepository.selectedGroup?.let {
            group = GroupHeaderModel(it)
        }
    }

    @SuppressLint("CheckResult")
    fun onResume() {
        val selectedGroup = localRepository.selectedGroup
        if (selectedGroup != null) {
            progressVisibility.postValue(true)
            networkRepository
                    .getTable(TableRequest(selectedGroup.nrec))
                    .compose(appModule.schedulersRepository.networkAsyncTransformer())
                    .subscribe({
                        studentsData.postValue(it.student)
                        progressVisibility.postValue(false)
                    }, errorHandler)

        }
    }

    fun onLogoutClick() {
        postRouterCommandQueue(RouterCommand.ShowDialog(R.string.logout, R.string.log_out_alert, Button(R.string.confirm) { appModule.logoutInteractor.logout(this) }))
    }

    fun onFabClick() {
        postRouterCommandQueue(RouterCommand.OpenScreen(Screen.SIGN), RouterCommand.Close())
    }
}