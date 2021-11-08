package com.prabhatkushwaha.mychef.framework.datasource.network.model

data class RecipeResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val recipes: List<RecipeNetworkModel>
)