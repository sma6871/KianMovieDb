package com.kian.movie.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Masoud Ashrafzadeh on 2017/11/10.
 */

enum class GlideScaleType {
    None, CenterCrop, FitCenter
}

fun ImageView.loadImageWithGlide(
    url: String,
    scaleType: GlideScaleType = GlideScaleType.None,
    diskCacheStrategy: DiskCacheStrategy = DiskCacheStrategy.ALL
) {
    this.context

    var options = RequestOptions().apply {
        diskCacheStrategy(diskCacheStrategy)
    }

    when (scaleType) {
        GlideScaleType.CenterCrop -> options = options.centerCrop()
        GlideScaleType.FitCenter -> options = options.fitCenter()
    }

    Glide.with(context)
        .applyDefaultRequestOptions(options)
        .load(url)
        .into(this)
}



