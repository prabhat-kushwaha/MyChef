package com.prabhatkushwaha.mychef.business.data.cache.abstraction

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.prabhatkushwaha.mychef.business.domain.model.Recipe
import com.prabhatkushwaha.mychef.framework.datasource.cache.model.RecipeCacheModel
import com.prabhatkushwaha.mychef.framework.datasource.network.paging.RecipeRemoteMediator
import kotlinx.coroutines.flow.Flow

interface RecipeCacheDataSource {

    suspend fun addAllRecipe(recipes: List<RecipeCacheModel>)
    suspend fun getAllRecipe(): LiveData<List<Recipe>>
    suspend fun deleteAllRecipe()

    @ExperimentalPagingApi
    suspend fun getRecipePagingSource(
        pagingConfig: PagingConfig,
        remoteMediator: RecipeRemoteMediator
    ): Flow<PagingData<Recipe>>


}