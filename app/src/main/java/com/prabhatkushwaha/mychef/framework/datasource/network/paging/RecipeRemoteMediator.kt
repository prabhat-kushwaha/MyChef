package com.prabhatkushwaha.mychef.framework.datasource.network.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.prabhatkushwaha.mychef.business.data.cache.abstraction.RecipeCacheDataSource
import com.prabhatkushwaha.mychef.business.data.network.abstarction.RecipeNetworkDataSource
import com.prabhatkushwaha.mychef.framework.datasource.cache.mapper.RecipeCacheMapper
import com.prabhatkushwaha.mychef.framework.datasource.cache.model.RecipeCacheModel
import com.prabhatkushwaha.mychef.framework.pref.RemoteKeyPrefManager


@ExperimentalPagingApi
class RecipeRemoteMediator constructor(
    private val search: String,
    private val skipInitialRefresh: Boolean = false,
    private val recipeNetworkDataSource: RecipeNetworkDataSource,
    private val recipeCacheDataSource: RecipeCacheDataSource,
    private val remoteKeyPrefManager: RemoteKeyPrefManager,
    private val recipeCacheMapper: RecipeCacheMapper
) : RemoteMediator<Int, RecipeCacheModel>() {

    companion object {
        private const val TAG = "RecipeRemoteMediator"
    }

    override suspend fun initialize(): InitializeAction {
        return if (skipInitialRefresh) InitializeAction.SKIP_INITIAL_REFRESH

        else InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RecipeCacheModel>
    ): MediatorResult {
        return try {


            val nextLoadKey: Any = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.APPEND -> {
                    if (remoteKeyPrefManager.isReachedToEnd()) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    remoteKeyPrefManager.getNextRemoteKey().toString().toIntOrNull() ?: 1
                }
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            }

            val pageNo = nextLoadKey.toString().toIntOrNull() ?: 1

            Log.i(TAG, "load: page no to be fetched:$pageNo")

            val resource = recipeNetworkDataSource.searchRecipe(
                query = search,
                page = pageNo,
            )
            if (resource.isNotEmpty()) {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyPrefManager.setNextRemoteKey(null)
                    recipeCacheDataSource.deleteAllRecipe()
                }

                resource.let {
                    recipeCacheDataSource.addAllRecipe(it.map { recipe ->
                        recipeCacheMapper.toEntity(recipe)
                    })
                }
                val isReachedToEnd = resource.size < state.config.pageSize
                remoteKeyPrefManager.setReachedToEnd(isReachedToEnd)

                if (!isReachedToEnd) {
                    remoteKeyPrefManager.setNextRemoteKey((pageNo + 1).toString())
                }

                MediatorResult.Success(
                    endOfPaginationReached = isReachedToEnd
                )
            } else {
                MediatorResult.Error(Throwable("Data Not Found"))
            }

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }

    }

}