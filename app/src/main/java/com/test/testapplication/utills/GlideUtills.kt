package com.test.testapplication.utills

import android.content.Context
import android.graphics.Bitmap
import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import java.io.File


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 10:25
 */

object GlideUtils {

    private val TAG = GlideUtils::class.java.javaClass.simpleName

    fun loadImage(context: Context, imagePath: String?, @DrawableRes placeHolderId: Int, imageView: ImageView) {

        val options = RequestOptions()
            .override(imageView.width)
            .error(placeHolderId)
            .placeholder(placeHolderId)

        Glide.with(context)
            .load(imagePath)
            .apply(options)
            .into(imageView)
    }
}