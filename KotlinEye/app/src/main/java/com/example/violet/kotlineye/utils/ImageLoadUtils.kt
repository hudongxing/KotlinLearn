package com.example.violet.kotlineye.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.violet.kotlineye.R

/**
 * Created by violet.
 * @ author VioLet.
 * @ email 286716191@qq.com
 * @ date 2017/11/13.
 */
class ImageLoadUtils{
    companion object{
        fun display(context: Context, imageView: ImageView?, url: String) {
            if (imageView == null) {
                throw IllegalArgumentException("argument error")
            }
            Glide.with(context).load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_image_loading)
                    .error(R.mipmap.ic_empty_picture)
                    .crossFade().into(imageView)
        }
        fun displayHigh(context: Context, imageView: ImageView?, url: String){
            if (imageView == null) {
                throw IllegalArgumentException("argument error")
            }
            Glide.with(context).load(url)
                    .asBitmap()
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_image_loading)
                    .error(R.mipmap.ic_empty_picture)
                    .into(imageView)
        }
    }

}