package com.otmanethedev.beers.data.models

import androidx.annotation.Keep

@Keep
data class Malt(
    val amount: Amount,
    val name: String
)