package com.mney.wallet.repository

import android.widget.ImageView

/**
 * Created by Pasenchuk Victor on 15/06/2017
 */
interface ImagesRepository {

    fun <T : ImageView> loadImage(view: T, url: String)

    fun <T : ImageView> loadImageDrawable(view: T, id: Int)

    fun prefetchImage(url: String)

}
