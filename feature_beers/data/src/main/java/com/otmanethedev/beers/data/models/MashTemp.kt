package com.otmanethedev.beers.data.models

import androidx.annotation.Keep

@Keep
data class MashTemp(
    val duration: Int,
    val temp: Temp
)