package com.prabhatkushwaha.mychef.framework.datasource.network.impl

import android.util.Log
import com.prabhatkushwaha.mychef.business.domain.model.Recipe
import com.prabhatkushwaha.mychef.business.domain.model.RecipeDetailsModel
import com.prabhatkushwaha.mychef.framework.datasource.network.abstaction.RecipeApiService
import com.prabhatkushwaha.mychef.framework.datasource.network.mapper.RecipeDetailsNetworkMapper
import com.prabhatkushwaha.mychef.framework.datasource.network.mapper.RecipeNetworkMapper
import com.prabhatkushwaha.mychef.framework.datasource.network.service.RecipeService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeApiServiceImpl @Inject constructor(
    private val recipeService: RecipeService,
    private val recipeNetworkMapper: RecipeNetworkMapper,
    private val recipeDetailsNetworkMapper: RecipeDetailsNetworkMapper
) : RecipeApiService {

    override suspend fun searchRecipe(query: String, page: Int): List<Recipe> {
        val apiResponse = recipeService.searchRecipe(query = query, page = page.toString())

        return apiResponse.recipes.map {
            recipeNetworkMapper.fromEntity(it)
        }
    }

    override suspend fun getRecipeById(id: String): RecipeDetailsModel {
        Log.d("TAG", "getRecipeById: $id")
        val apiResponse = recipeService.getRecipeById(recipe_id = id)
        Log.d("TAG", "getRecipeById: $apiResponse")
        return recipeDetailsNetworkMapper.fromEntity(apiResponse)
    }
}