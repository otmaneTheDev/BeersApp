package com.otmanethedev.beers.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Beer(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String?,
    val date: String,
    val abv: Float,
    val ph: Float,
    val malts: List<String>,
    val hops: List<String>,
) : Parcelable