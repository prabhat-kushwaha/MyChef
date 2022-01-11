package com.prabhatkushwaha.mychef.business.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeLikedModel(val recipeName: String) : Parcelable
