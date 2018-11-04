package com.omstu.biznessapp.repository.implementations

import android.content.Context
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.omstu.biznessapp.repository.ImagesRepository
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.lang.Exception


class ImagesRepositoryImpl(val context: Context) : ImagesRepository {

    override fun <T : ImageView> loadImage(view: T, url: String) = Picasso.get()
            .load(url)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(view, object : Callback {
                override fun onError(e: Exception?) {
                    Picasso.get().load(url).networkPolicy(NetworkPolicy.NO_CACHE)
                            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                            .into(view)
                }

                override fun onSuccess() {
                }
            })

    override fun <T : ImageView> loadImageDrawable(view: T, id: Int) = view.setImageDrawable(ContextCompat.getDrawable(view.context, id))


    override fun prefetchImage(url: String) = Picasso
            .get()
            .load(url)
            .fetch();
}
