package com.xavidev.pokeapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.xavidev.pokeapp.R

object BindingAdapters {

    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImage(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            Picasso.get().load(url).into(view)
        } else {
            Picasso.get().load(R.drawable.ic_discover)
        }
    }
}