package com.prabhatkushwaha.mychef.framework.datasource.network.abstaction

import com.prabhatkushwaha.mychef.business.domain.model.Recipe
import com.prabhatkushwaha.mychef.business.domain.model.RecipeDetailsModel

interface RecipeApiService {
    suspend fun searchRecipe(query: String, page: Int): List<Recipe>
    suspend fun getRecipeById(id:String): RecipeDetailsModel
}