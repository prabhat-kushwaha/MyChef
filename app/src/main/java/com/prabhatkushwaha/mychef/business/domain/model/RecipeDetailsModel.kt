package com.prabhatkushwaha.mychef.business.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeDetailsModel(
    val _id: String,
    val image_url: String?,
    val ingredients: String?,
    val publisher: String?,
    val publisher_url: String?,
    val recipe_id: String?,
    val social_rank: Double?,
    val source_url: String?,
    val title: String?,
    val toolbarColor:Int?
): Parcelable