package com.project.qasir.common.shared

import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageHelper {

    companion object {
        fun setResource(url: String, imageView: ImageView) {
            Glide.with(imageView.context)
                    .load(url)
                    .centerCrop()
                    .into(imageView)
        }
    }

}