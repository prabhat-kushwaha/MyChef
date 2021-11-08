package com.prabhatkushwaha.mychef.framework.datasource.cache.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.*
import com.prabhatkushwaha.mychef.business.domain.model.Recipe
import com.prabhatkushwaha.mychef.framework.datasource.cache.abstarction.RecipeDaoService
import com.prabhatkushwaha.mychef.framework.datasource.cache.database.RecipeDao
import com.prabhatkushwaha.mychef.framework.datasource.cache.mapper.RecipeCacheMapper
import com.prabhatkushwaha.mychef.framework.datasource.cache.model.RecipeCacheModel
import com.prabhatkushwaha.mychef.framework.datasource.network.paging.RecipeRemoteMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalPagingApi
@Singleton
class RecipeDaoServiceImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val recipeCacheMapper: RecipeCacheMapper
) : RecipeDaoService {

    override suspend fun addAllRecipe(recipes: List<RecipeCacheModel>) {
        recipeDao.insertRecipes(recipes)
    }

    override suspend fun deleteAllRecipe() {
        recipeDao.deleteAllRecipe()
    }

    override suspend fun getAllRecipe(): LiveData<List<Recipe>> {
        return recipeDao.getAllRecipe().map {
            it.map { recipeCache ->
                recipeCacheMapper.fromEntity(recipeCache)
            }
        }
    }

    override suspend fun getRecipePagingSource(
        pagingConfig: PagingConfig,
        remoteMediator: RecipeRemoteMediator
    ): Flow<PagingData<Recipe>> {
        return Pager(pagingConfig, remoteMediator = remoteMediator) {
            recipeDao.getRecipePagingSource()
        }.flow.map { pagingData ->
            pagingData.map {
                recipeCacheMapper.fromEntity(it)
            }
        }
    }
}