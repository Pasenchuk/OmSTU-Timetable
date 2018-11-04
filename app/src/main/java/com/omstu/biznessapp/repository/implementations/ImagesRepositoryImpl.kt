package com.mney.wallet.repository.implementations

import android.content.Context
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.mney.wallet.repository.ImagesRepository
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso


class ImagesRepositoryImpl(val context: Context) : ImagesRepository {

    override fun <T : ImageView> loadImage(view: T, url: String) = Picasso
            .with(view.context)
            .load(url)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(view, object : Callback {
                override fun onSuccess() {
                }

                override fun onError() {
                    Picasso.with(view.context).load(url).networkPolicy(NetworkPolicy.NO_CACHE)
                            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                            .into(view)
                }
            })

    override fun <T : ImageView> loadImageDrawable(view: T, id: Int) = view.setImageDrawable(ContextCompat.getDrawable(view.context, id))


    override fun prefetchImage(url: String) = Picasso
            .with(context)
            .load(url)
            .fetch();
}
