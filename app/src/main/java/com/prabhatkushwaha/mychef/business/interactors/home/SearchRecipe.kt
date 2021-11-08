package com.prabhatkushwaha.mychef.business.interactors.home

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.prabhatkushwaha.mychef.business.data.cache.abstraction.RecipeCacheDataSource
import com.prabhatkushwaha.mychef.business.data.network.abstarction.RecipeNetworkDataSource
import com.prabhatkushwaha.mychef.business.domain.model.Recipe
import com.prabhatkushwaha.mychef.framework.datasource.cache.mapper.RecipeCacheMapper
import com.prabhatkushwaha.mychef.framework.datasource.network.paging.RecipeRemoteMediator
import com.prabhatkushwaha.mychef.framework.pref.RemoteKeyPrefManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalPagingApi
class SearchRecipe @Inject constructor(
    private val recipeCacheDataSource: RecipeCacheDataSource,
    private val recipeNetworkDataSource: RecipeNetworkDataSource,
    private val remoteKeyPrefManager: RemoteKeyPrefManager,
    private val recipeCacheMapper: RecipeCacheMapper

) {

    fun getRecipeList(search: String, pagingConfig: PagingConfig): Flow<PagingData<Recipe>> {

        return flow {
            val mediator = RecipeRemoteMediator(
                search = search,
                //skip initial refresh when data in local is more then PAGE_SIZE
                skipInitialRefresh = (recipeCacheDataSource.getAllRecipe().value?.size
                    ?: 0) > pagingConfig.pageSize,
                recipeNetworkDataSource = recipeNetworkDataSource,
                recipeCacheDataSource = recipeCacheDataSource,
                remoteKeyPrefManager = remoteKeyPrefManager,
                recipeCacheMapper = recipeCacheMapper
            )

            emitAll(
                recipeCacheDataSource.getRecipePagingSource(
                    pagingConfig,
                    mediator
                )
            )
        }.flowOn(Dispatchers.Default)
    }

}