package com.prabhatkushwaha.mychef.business.data.cache.impl

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.prabhatkushwaha.mychef.business.data.cache.abstraction.RecipeCacheDataSource
import com.prabhatkushwaha.mychef.business.domain.model.Recipe
import com.prabhatkushwaha.mychef.business.domain.model.RecipeDetailsModel
import com.prabhatkushwaha.mychef.business.domain.model.RecipeLikedModel
import com.prabhatkushwaha.mychef.framework.datasource.cache.abstarction.RecipeDaoService
import com.prabhatkushwaha.mychef.framework.datasource.cache.model.RecipeCacheModel
import com.prabhatkushwaha.mychef.framework.datasource.network.paging.RecipeRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalPagingApi
@Singleton
class RecipeCacheDataSourceImpl @Inject constructor(private val recipeDaoService: RecipeDaoService) :
    RecipeCacheDataSource {




    override suspend fun addAllRecipe(recipes: List<RecipeCacheModel>) {
        return recipeDaoService.addAllRecipe(recipes)
    }

    override suspend fun getAllRecipe(): LiveData<List<Recipe>> {
       return recipeDaoService.getAllRecipe()
    }

    override suspend fun deleteAllRecipe() {
        recipeDaoService.deleteAllRecipe()
    }

    override suspend fun getRecipePagingSource(
        pagingConfig: PagingConfig,
        remoteMediator: RecipeRemoteMediator
    ): Flow<PagingData<Recipe>> {
       return recipeDaoService.getRecipePagingSource(pagingConfig,remoteMediator)
    }


}