package com.otmanethedev.beers.data.models

import androidx.annotation.Keep
import com.otmanethedev.beers.domain.models.Beer

@Keep
data class BeersResponseDtoItem(
    val abv: Double,
    val attenuation_level: Double,
    val boil_volume: BoilVolume,
    val brewers_tips: String,
    val contributed_by: String,
    val description: String,
    val ebc: Double,
    val first_brewed: String,
    val food_pairing: List<String>,
    val ibu: Double,
    val id: Int,
    val image_url: String?,
    val ingredients: Ingredients,
    val method: Method,
    val name: String,
    val ph: Double,
    val srm: Double,
    val tagline: String,
    val target_fg: Int,
    val target_og: Double,
    val volume: Volume
)

fun BeersResponseDtoItem.toDomain(): Beer {
    return Beer(
        id,
        name,
        description,
        image_url,
        first_brewed,
        abv.toFloat(),
        ph.toFloat(),
        ingredients.malt.map { it.name },
        ingredients.hops.map { it.name }
    )
}