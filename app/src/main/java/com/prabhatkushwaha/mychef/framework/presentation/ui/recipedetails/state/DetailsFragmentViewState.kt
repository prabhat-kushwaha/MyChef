package com.prabhatkushwaha.mychef.framework.presentation.ui.recipedetails.state

import android.os.Parcelable
import com.prabhatkushwaha.mychef.business.domain.model.Recipe
import com.prabhatkushwaha.mychef.business.domain.model.RecipeDetailsModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsFragmentViewState(
    var recipe: RecipeDetailsModel? = null,
    var recipeDetailsId: String? = null,
    var isRecipeSaved:Boolean?=null
) : Parcelable
