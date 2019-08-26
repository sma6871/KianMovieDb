package com.kian.movie.ui.details

import android.os.Bundle
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import com.kian.movie.R
import com.kian.movie.constants.ApplicationConstants
import com.kian.movie.constants.BundleExtraKeys
import com.kian.movie.data.models.MovieItem
import com.kian.movie.extensions.GlideScaleType
import com.kian.movie.extensions.fromJson
import com.kian.movie.extensions.loadImageWithGlide
import com.kian.movie.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.list_item.txtTitle

class DetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }

    override fun onStart() {
        super.onStart()
        loadInfo()
    }

    /**
     * This method loads movie info from intent
     * */
    private fun loadInfo() {
        intent?.let {
            val movie: MovieItem? =
                Gson().fromJson(it.getStringExtra(BundleExtraKeys.MovieData) ?: "")
            movie?.let { item ->
                txtTitle.text = item.title
                txtDescription.text = item.overview
                if (!item.backdropPath.isNullOrBlank())
                    imgBackDrop.loadImageWithGlide(
                        ApplicationConstants.BACKDROP_BASE_URL + item.backdropPath,
                        diskCacheStrategy = DiskCacheStrategy.ALL,
                        scaleType = GlideScaleType.CenterCrop
                    )
                if (!item.posterPath.isNullOrBlank())
                    imgPoster.loadImageWithGlide(
                        ApplicationConstants.POSTER_BASE_URL + item.posterPath,
                        diskCacheStrategy = DiskCacheStrategy.ALL,
                        scaleType = GlideScaleType.CenterCrop
                    )
            }


        }
    }
}
