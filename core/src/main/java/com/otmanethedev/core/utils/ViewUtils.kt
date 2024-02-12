package com.otmanethedev.core.utils

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.otmanethedev.core.R

fun AppCompatImageView.loadFromUrl(url: String?) {
    Glide.with(this).load(url)
        .placeholder(R.drawable.img_placeholder)
        .into(this)
}