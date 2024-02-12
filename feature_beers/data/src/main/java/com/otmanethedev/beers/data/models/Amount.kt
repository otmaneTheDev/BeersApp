package com.otmanethedev.beers.data.models

import androidx.annotation.Keep

@Keep
data class Amount(
    val unit: String,
    val value: Double
)