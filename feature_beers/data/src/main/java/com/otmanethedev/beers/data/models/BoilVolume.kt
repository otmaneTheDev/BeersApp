package com.otmanethedev.beers.data.models

import androidx.annotation.Keep

@Keep
data class BoilVolume(
    val unit: String,
    val value: Int
)