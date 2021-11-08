package com.prabhatkushwaha.mychef.framework.presentation.ui.home.state

import android.os.Parcelable
import com.prabhatkushwaha.mychef.business.domain.model.Recipe
import kotlinx.parcelize.Parcelize


@Parcelize
data class HomeFragmentViewState(
    var recipe: Recipe? = null,
    var searchQuery: String? = null
) : Parcelable
