package com.omstu.biznessapp.domain

import com.omstu.biznessapp.network.response.Group

class GroupHeaderModel(group: Group) {
    val discipline = group.discipline
    val subdivision = "${group.listChair}; ${group.listFacult}"
    val teacher = group.examiner
    val title = "${group.studGroup}, ${group.semester} ${group.year}"
}