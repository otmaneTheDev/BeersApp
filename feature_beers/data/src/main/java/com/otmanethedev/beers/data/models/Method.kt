package com.otmanethedev.beers.data.models

import androidx.annotation.Keep

@Keep
data class Method(
    val fermentation: Fermentation,
    val mash_temp: List<MashTemp>,
    val twist: String
)