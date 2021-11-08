package com.prabhatkushwaha.mychef.business.data.network.abstarction

import com.prabhatkushwaha.mychef.business.domain.model.Recipe
import com.prabhatkushwaha.mychef.business.domain.model.RecipeDetailsModel

interface RecipeNetworkDataSource {

    suspend fun searchRecipe(query: String,page:Int): List<Recipe>

    suspend fun getRecipeById(id:String): RecipeDetailsModel
}