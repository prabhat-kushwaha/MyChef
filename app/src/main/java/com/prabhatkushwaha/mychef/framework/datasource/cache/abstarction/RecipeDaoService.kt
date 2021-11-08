package com.prabhatkushwaha.mychef.framework.datasource.cache.abstarction

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.prabhatkushwaha.mychef.business.domain.model.Recipe
import com.prabhatkushwaha.mychef.framework.datasource.cache.model.RecipeCacheModel
import com.prabhatkushwaha.mychef.framework.datasource.network.paging.RecipeRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@ExperimentalPagingApi
@Singleton
interface RecipeDaoService {
    suspend fun addAllRecipe(recipes: List<RecipeCacheModel>)
    suspend fun deleteAllRecipe()
    suspend fun getAllRecipe(): LiveData<List<Recipe>>
    suspend fun getRecipePagingSource(pagingConfig: PagingConfig, remoteMediator: RecipeRemoteMediator): Flow<PagingData<Recipe>>

}