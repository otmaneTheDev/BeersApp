package com.otmanethedev.beers.data.models

import androidx.annotation.Keep

@Keep
data class Temp(
    val unit: String,
    val value: Int
)