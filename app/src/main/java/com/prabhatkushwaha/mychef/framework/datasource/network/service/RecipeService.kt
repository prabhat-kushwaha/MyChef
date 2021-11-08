package com.prabhatkushwaha.mychef.framework.datasource.network.service

import com.prabhatkushwaha.mychef.BuildConfig
import com.prabhatkushwaha.mychef.framework.datasource.network.model.RecipeDetailsNetworkModel
import com.prabhatkushwaha.mychef.framework.datasource.network.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {




    @GET(BuildConfig.SEARCH_END_POINT)
    suspend fun searchRecipe(
        @Query("q")  query:String,
        @Query("page")  page:String
    ): RecipeResponse

    @GET(BuildConfig.RECIPE_DETAILS_END_POINT)
    suspend fun getRecipeById(
        @Query("rId") recipe_id:String
        ): RecipeDetailsNetworkModel




}


