package com.omstu.biznessapp.ui.table

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.omstu.biznessapp.R
import com.omstu.biznessapp.di.AppModule
import com.omstu.biznessapp.ui.base.BaseActivity
import com.omstu.biznessapp.ui.base.BasePresenter


class TableActivity : BaseActivity() {

    private lateinit var presenter: TablePresenter

    override fun initPresenter(appModule: AppModule): BasePresenter = TablePresenter(appModule)


    private lateinit var vStudents: RecyclerView
    private lateinit var vDiscipline: TextView
    private lateinit var vSubdivision: TextView
    private lateinit var vTeacher: TextView
    private lateinit var vGroupTitle: TextView
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        vStudents = findViewById(R.id.v_students)
        vDiscipline = findViewById(R.id.v_discipline)
        vSubdivision = findViewById(R.id.v_subdivision)
        vTeacher = findViewById(R.id.v_teacher)
        vGroupTitle = findViewById(R.id.v_group_title)
        fab = findViewById(R.id.fab)

        presenter = getPresenter(TablePresenter::class.java)

        initViews()
    }

    private fun initViews() {
        vStudents.layoutManager = LinearLayoutManager(this)
        vStudents.adapter = TableAdapter(this, presenter)


        presenter.group?.apply {
            vGroupTitle.text = title
            vDiscipline.text = discipline
            vSubdivision.text = subdivision
            vTeacher.text = teacher
        }

        fab.setOnClickListener { presenter.onFabClick() }
    }


    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }
}
