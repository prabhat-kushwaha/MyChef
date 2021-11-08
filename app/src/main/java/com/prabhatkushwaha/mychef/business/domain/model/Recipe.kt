package com.prabhatkushwaha.mychef.business.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Recipe(
    val image_url: String?,
    val social_rank: String?,
    val _id: String,
    val publisher: String?,
    val source_url: String?,
    val recipe_id:String,
    val publisher_url: String?,
    val title: String?,
): Parcelable