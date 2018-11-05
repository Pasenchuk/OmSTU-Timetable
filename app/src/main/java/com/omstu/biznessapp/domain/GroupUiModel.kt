package com.omstu.biznessapp.domain

import com.omstu.biznessapp.network.response.Group

data class GroupUiModel(
        val title: String,
        val subTitle: String,
        val subdivision: String,
        val footer: String,
        val group: Group
)