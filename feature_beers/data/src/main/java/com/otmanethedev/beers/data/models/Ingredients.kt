package com.otmanethedev.beers.data.models

import androidx.annotation.Keep

@Keep
data class Ingredients(
    val hops: List<Hop>,
    val malt: List<Malt>,
    val yeast: String
)