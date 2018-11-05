package com.omstu.biznessapp.ui.groups

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import com.omstu.biznessapp.R
import com.omstu.biznessapp.di.AppModule
import com.omstu.biznessapp.domain.GroupUiModel
import com.omstu.biznessapp.network.request.GroupsRequest
import com.omstu.biznessapp.network.response.Group
import com.omstu.biznessapp.router.Button
import com.omstu.biznessapp.router.RouterCommand
import com.omstu.biznessapp.ui.base.BasePresenter

class GroupsPresenter(appModule: AppModule) : BasePresenter(appModule) {

    val groupsData = MutableLiveData<List<GroupUiModel>>()

    init {
        groupsData.postValue(listOf())
    }

    @SuppressLint("CheckResult")
    fun onResume() {
        progressVisibility.postValue(true)
        networkRepository
                .getGroups(GroupsRequest(localRepository.fnpp))
                .map { groups ->
                    groups
                            .sortedWith(Comparator { p1, p0 ->
                                p0.year.compareTo(p1.year)
                            })
                            .map {
                                it.run {
                                    val state = when (status) {
                                        0 -> appModule.resourceRepository.invoke(R.string.new_table)
                                        1 -> appModule.resourceRepository.invoke(R.string.open_table)
                                        else -> appModule.resourceRepository.invoke(R.string.closed_table)
                                    }
                                    GroupUiModel(
                                            title = discipline,
                                            subTitle = "$year, $semester, $state",
                                            subdivision = "$studGroup; $listChair; $listFacult",
                                            footer = examiner,
                                            group = it
                                    )
                                }
                            }
                }
                .compose(appModule.schedulersRepository.networkAsyncTransformer())
                .subscribe({
                    groupsData.postValue(it)
                    progressVisibility.postValue(false)
                }, errorHandler)
    }

    fun onGroupClicked(group: Group) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onLogoutClick() {
        postRouterCommandQueue(RouterCommand.ShowDialog(R.string.logout, R.string.log_out_alert, Button(R.string.confirm) { appModule.logoutInteractor.logout(this) }))
    }
}