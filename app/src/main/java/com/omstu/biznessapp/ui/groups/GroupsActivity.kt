package com.omstu.biznessapp.ui.groups

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.omstu.biznessapp.R
import com.omstu.biznessapp.di.AppModule
import com.omstu.biznessapp.ui.base.BaseActivity
import com.omstu.biznessapp.ui.base.BasePresenter
import kotlinx.android.synthetic.main.activity_groups.*

class GroupsActivity : BaseActivity() {

    private lateinit var presenter: GroupsPresenter

    override fun initPresenter(appModule: AppModule): BasePresenter = GroupsPresenter(appModule)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)


        presenter = getPresenter(GroupsPresenter::class.java)

        v_groups.layoutManager = LinearLayoutManager(this)
        v_groups.adapter = GroupsAdapter(this, presenter)


        R.id.v_logout calls {
            presenter.onLogoutClick()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }
}
