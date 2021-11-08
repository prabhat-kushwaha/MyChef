package com.prabhatkushwaha.mychef.business.data.network.impl

import com.prabhatkushwaha.mychef.business.data.network.abstarction.RecipeNetworkDataSource
import com.prabhatkushwaha.mychef.business.domain.model.Recipe
import com.prabhatkushwaha.mychef.business.domain.model.RecipeDetailsModel
import com.prabhatkushwaha.mychef.framework.datasource.network.abstaction.RecipeApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeNetworkDataSourceImpl @Inject constructor(private val recipeApiService: RecipeApiService) :
    RecipeNetworkDataSource {
    override suspend fun searchRecipe(query: String, page: Int): List<Recipe> {
        return recipeApiService.searchRecipe(query, page)
    }

    override suspend fun getRecipeById(id: String): RecipeDetailsModel {
        return recipeApiService.getRecipeById(id)
    }
}