package com.omstu.biznessapp.ui.table

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.omstu.biznessapp.R
import com.omstu.biznessapp.di.AppModule
import com.omstu.biznessapp.ui.base.BaseActivity
import com.omstu.biznessapp.ui.base.BasePresenter
import kotlinx.android.synthetic.main.activity_table.*

class TableActivity : BaseActivity() {

    private lateinit var presenter: TablePresenter

    override fun initPresenter(appModule: AppModule): BasePresenter = TablePresenter(appModule)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)


        presenter = getPresenter(TablePresenter::class.java)

        v_students.layoutManager = LinearLayoutManager(this)
        v_students.adapter = TableAdapter(this, presenter)


    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }
}
