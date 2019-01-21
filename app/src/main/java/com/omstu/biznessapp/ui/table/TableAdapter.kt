package com.omstu.biznessapp.ui.table

import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.omstu.biznessapp.R
import com.omstu.biznessapp.network.response.StudentItem
import com.omstu.biznessapp.ui.base.BaseActivity


class TableAdapter(val activity: BaseActivity, val tablePresenter: TablePresenter) : RecyclerView.Adapter<TableAdapter.GroupViewHolder>() {


    private var data: List<StudentItem> = listOf()

    init {
        tablePresenter.studentsData.observe(activity, Observer<List<StudentItem>> { list ->
            list?.let {
                data = it
                notifyDataSetChanged()
            }
        })
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GroupViewHolder {
        val mView = LayoutInflater.from(p0.context).inflate(R.layout.content_student, p0, false)
        return GroupViewHolder(mView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(p0: GroupViewHolder, p1: Int) {
        p0.fillView(data[p1], tablePresenter)
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = data[position].markStudNrec.hashCode().toLong()

    class GroupViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val vName = view.findViewById<View>(R.id.v_name) as TextView
        private val vTotalHours = view.findViewById<View>(R.id.v_total_hours) as EditText
        private val vPrecent = view.findViewById<View>(R.id.v_precent) as EditText
        private val vRating = view.findViewById<View>(R.id.v_rating) as EditText


        fun fillView(uiModel: StudentItem, tablePresenter: TablePresenter) {
            uiModel.apply {
                vName.text = fio
                vTotalHours.setText(totalStudHours.toString())
                vPrecent.setText(percent.toString())
                vRating.setText(rating.toString())

                RxTextView.afterTextChangeEvents(vRating).subscribe {
                    uiModel.rating = Integer.valueOf(it.editable().toString())
                    tablePresenter.onEntryChanged(uiModel)
                }
                RxTextView.afterTextChangeEvents(vPrecent).subscribe {
                    uiModel.percent = Integer.valueOf(it.editable().toString())
                    tablePresenter.onEntryChanged(uiModel)
                }
                RxTextView.afterTextChangeEvents(vTotalHours).subscribe {
                    uiModel.totalStudHours = Integer.valueOf(it.editable().toString())
                    tablePresenter.onEntryChanged(uiModel)
                }
            }
        }
    }

}