package com.omstu.biznessapp.ui.groups

import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.omstu.biznessapp.R
import com.omstu.biznessapp.domain.GroupUiModel
import com.omstu.biznessapp.ui.base.BaseActivity


class GroupsAdapter(val activity: BaseActivity, val groupsPresenter: GroupsPresenter) : RecyclerView.Adapter<GroupsAdapter.GroupViewHolder>() {


    private var data: List<GroupUiModel> = listOf()

    init {
        groupsPresenter.groupsData.observe(activity, Observer<List<GroupUiModel>> { list ->
            list?.let {
                data = it
                notifyDataSetChanged()
            }
        })
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GroupViewHolder {
        val mView = LayoutInflater.from(p0.context).inflate(R.layout.content_groups, p0, false)
        return GroupViewHolder(mView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(p0: GroupViewHolder, p1: Int) {
        p0.fillView(data[p1], groupsPresenter)
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = data[position].group.nrec.hashCode().toLong()

    class GroupViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var vGroupContainer: View = view.findViewById<View>(R.id.v_group_container)
        private var vDiscipline: TextView = view.findViewById<View>(R.id.v_discipline) as TextView
        private var vPeriod: TextView = view.findViewById<View>(R.id.v_period) as TextView
        private var vSubdivision: TextView = view.findViewById<View>(R.id.v_subdivision) as TextView
        private var vGroupFooter: TextView = view.findViewById<View>(R.id.v_group_footer) as TextView

        fun fillView(uiModel: GroupUiModel, groupsPresenter: GroupsPresenter) {
            uiModel.apply {
                vDiscipline.text = title
                vPeriod.text = subTitle
                vSubdivision.text = subdivision
                vGroupFooter.text = footer

                vGroupContainer.setOnClickListener { groupsPresenter.onGroupClicked(group) }
            }
        }
    }

}